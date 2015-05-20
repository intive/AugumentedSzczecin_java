package com.bls.resource;

import com.bls.core.event.Event;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.bls.core.poi.Category;
import com.bls.dao.EventDao;
import com.bls.dao.PersonDao;
import com.bls.dao.PlaceDao;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SearchService {

    private final EventDao<Event> eventDao;
    private final PlaceDao<Place> placeDao;
    private final PersonDao<Person> personDao;

    @Inject
    public SearchService(final EventDao eventDao, final PlaceDao placeDao, final PersonDao personDao) {
        this.eventDao = eventDao;
        this.placeDao = placeDao;
        this.personDao = personDao;
    }

    public SearchingResults searchByCriteria(final SearchCriteria searchCriteria) {
        final SearchingResults response = new SearchingResults();
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.EVENT)) {
            response.putEvents(eventDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(),
                                searchCriteria.getUser()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PERSON)) {
            response.putPerson(personDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(),
                                searchCriteria.getUser()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PLACE)) {
            response.putPlaces(placeDao.find(searchCriteria.getLocation(), searchCriteria.getRadius(), searchCriteria.getTags(),
                                searchCriteria.getUser()));
        }
        return response;
    }
}
