package com.bls.resource;

import com.bls.core.event.Event;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.bls.core.place_monument.PlaceMonument;
import com.bls.core.poi.Category;
import com.bls.dao.EventDao;
import com.bls.dao.PersonDao;
import com.bls.dao.PlaceDao;
import com.bls.dao.PlaceMonumentDao;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Rado on 2015-04-19.
 */
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

  /*  public static SearchService of(final SearchCriteria searchCriteria) {
        return new SearchService(searchCriteria);
    } */

    public SearchingResults searchByCriteria(final SearchCriteria searchCriteria) {
        final SearchingResults response = new SearchingResults();
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.EVENT)) {
            response.putEvents(eventDao.findInRadius(searchCriteria.getLocation(), searchCriteria.getRadius()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PERSON)) {
            response.putPerson(personDao.findInRadius(searchCriteria.getLocation(), searchCriteria.getRadius()));
        }
        if (searchCriteria.getCategories().isEmpty() || searchCriteria.getCategories().contains(Category.PLACE)) {
            response.putPlaces(placeDao.findInRadius(searchCriteria.getLocation(), searchCriteria.getRadius()));
        }
        if (searchCriteria.getCategories().contains(Category.PLACE_MONUMENT)){
            response.putPlaceMonuments(placeMonumentDao.findInRadius(searchCriteria.getLocation(), searchCriteria.getRadius()));
        }

        return response;
    }
}
