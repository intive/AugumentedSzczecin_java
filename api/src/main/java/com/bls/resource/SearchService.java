package com.bls.resource;

import com.bls.core.commercial.Commercial;
import com.bls.core.event.Event;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.bls.core.poi.Category;
import com.bls.core.user.User;
import com.bls.dao.*;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchService {

    private final EventDao<Event> eventDao;
    private final PlaceDao<Place> placeDao;
    private final PersonDao<Person> personDao;
    private final CommercialDao<Commercial> commercialDao;
    private final UserDao<User<String>> userDao;

    @Inject
    public SearchService(final EventDao eventDao, final PlaceDao placeDao, final PersonDao personDao, final CommercialDao commercialDao, final UserDao userDao) {
        this.eventDao = eventDao;
        this.placeDao = placeDao;
        this.personDao = personDao;
        this.commercialDao = commercialDao;
        this.userDao = userDao;
    }

    public SearchingResults searchByCriteria(final SearchCriteria searchCriteria) {
        Optional<User<String>> user = searchCriteria.getUser();
        if(user.isPresent()) {
            user = userDao.findByEmail(user.get().getEmail());
        }

        final SearchingResults response = new SearchingResults();
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.EVENT)) {
            response.putEvents(eventDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user, searchCriteria.getPage(), searchCriteria.getPageSize()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PERSON)) {
            response.putPerson(personDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user, searchCriteria.getPage(), searchCriteria.getPageSize()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PLACE)) {
            response.putPlaces(placeDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user, searchCriteria.getPage(), searchCriteria.getPageSize()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.COMMERCIAL)) {
            response.putCommercials(commercialDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user));
        }
        return response;
    }
}
