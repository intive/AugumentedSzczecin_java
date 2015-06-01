package com.bls.service;

import com.bls.core.commercial.Commercial;
import com.bls.core.event.Event;
import com.bls.core.geo.Location;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.bls.core.poi.Category;
import com.bls.core.search.SearchCriteria;
import com.bls.core.search.SearchResults;
import com.bls.core.user.User;
import com.bls.dao.*;
import com.google.common.base.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class SearchService {

    private final EventDao<Event> eventDao;
    private final PlaceDao<Place> placeDao;
    private final PersonDao<Person> personDao;
    private final CommercialDao<Commercial> commercialDao;
    private final UserDao<User> userDao;

    @Inject
    public SearchService(final EventDao eventDao,
            final PlaceDao placeDao,
            final PersonDao personDao,
            final CommercialDao commercialDao,
            final UserDao userDao) {
        this.eventDao = eventDao;
        this.placeDao = placeDao;
        this.personDao = personDao;
        this.commercialDao = commercialDao;
        this.userDao = userDao;
    }

    public SearchResults searchByCriteria(final SearchCriteria searchCriteria) {
        Optional<User> user = searchCriteria.getUser();
        if (user.isPresent()) {
            user = userDao.findByEmail(user.get().getEmail());
        }

        final SearchResults response = new SearchResults();

        final Location location = searchCriteria.getLocation();
        final Long radius = searchCriteria.getRadius();
        final Collection<String> tags = searchCriteria.getTags();
        final Optional<String> name = searchCriteria.getName();
        final Optional<String> street = searchCriteria.getStreet();
        final Collection<Place.Subcategory> subcat = searchCriteria.getSubcat();
        final Optional<Boolean> paid = searchCriteria.getPaid();
        final Optional<Boolean> open = searchCriteria.getOpen();
        final Optional<Integer> page = searchCriteria.getPage();
        final Integer pageSize = searchCriteria.getPageSize();

        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.EVENT)) {
            response.putEvents(eventDao.find(location, radius, tags, name, street, subcat, paid, open, user, page, pageSize));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PERSON)) {
            response.putPerson(personDao.find(location, radius, tags, name, street, subcat, paid, open, user, page, pageSize));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PLACE)) {
            response.putPlaces(placeDao.find(location, radius, tags, name, street, subcat, paid, open, user, page, pageSize));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.COMMERCIAL)) {
            response.putCommercials(commercialDao.find(location, radius, tags, name, street, subcat, paid, open, user, page, pageSize));
        }
        return response;
    }
}
