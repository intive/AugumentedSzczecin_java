package com.bls.resource;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bls.core.event.Event;
import com.bls.core.geo.Location;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.bls.core.poi.Category;
import com.bls.core.user.User;
import com.bls.dao.EventDao;
import com.bls.dao.PersonDao;
import com.bls.dao.PlaceDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Search entities by geo location for logged in user.
 */
@Singleton
@Path("/q")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    // TODO move to Service and inject Service
    private final EventDao<Event> eventDao;
    private final PlaceDao<Place> placeDao;
    private final PersonDao<Person> personDao;

    @Inject
    public SearchResource(final EventDao eventDao, final PlaceDao placeDao, final PersonDao personDao) {
        this.eventDao = eventDao;
        this.placeDao = placeDao;
        this.personDao = personDao;
    }

    // TODO if (user == null) then it's unauthorized attempt

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public SearchingResults getByRegion(@Auth(required = false) User user,
            @QueryParam("lg") Float longitude,
            @QueryParam("lt") Float latitude,
            @QueryParam("radius") Long radius,
            @QueryParam("cat") List<Category> categories
            // TODO add categories criteria...
            // TODO add sorting, batching results...
    ) {

        // TODO build SearchCriteria (create some new class) object which can be validate and will hold search parameters

        SearchCriteria searchCriteria = new SearchCriteria(new Location(longitude, latitude), radius, categories);

        // TODO should be something like that: SearchService.of(searchCriteria.validate()).searchByCriteria();
        final SearchingResults response = new SearchingResults();

        return response;
    }
}
