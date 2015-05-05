package com.bls.resource;

import com.bls.core.event.Event;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.bls.core.place_monument.PlaceMonument;
import com.bls.core.poi.Category;
import com.bls.core.user.User;
import com.bls.dao.EventDao;
import com.bls.dao.PersonDao;
import com.bls.dao.PlaceDao;
import com.bls.dao.PlaceMonumentDao;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchService {

    private final EventDao<Event> eventDao;
    private final PlaceDao<Place> placeDao;
    private final PersonDao<Person> personDao;
    private final PlaceMonumentDao<PlaceMonument> placeMonumentDao;

    @Inject
    public SearchService(final EventDao eventDao, final PlaceDao placeDao, final PersonDao personDao, final PlaceMonumentDao placeMonumentDao) {
        this.eventDao = eventDao;
        this.placeDao = placeDao;
        this.personDao = personDao;
        this.placeMonumentDao = placeMonumentDao;
    }

    public SearchingResults searchByCriteria(final SearchCriteria searchCriteria, final User user) {
        final SearchingResults response = new SearchingResults();
        if (user!=null) {
            if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.EVENT)) {
                response.putEvents(eventDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user));
            }
            if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PERSON)) {
                response.putPerson(personDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user));
            }
            if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PLACE)) {
                response.putPlaces(placeDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user));
            }
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PLACE_MONUMENT) || user==null){
            response.putPlaceMonuments(placeMonumentDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(), user));
        }
        return response;
    }
}
