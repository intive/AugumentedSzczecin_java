package com.bls.resource;

import com.bls.core.geo.Location;
import com.bls.core.place.Place;
import com.bls.core.user.User;
import com.bls.dao.PlaceDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/places")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlacesResource {

    private final PlaceDao<Place> placeDao;

    @Inject
    public PlacesResource(final PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    @Path("/q")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Place> getByRegion(@Auth User user, @QueryParam("lg") Float longitude,
                                   @QueryParam("lt") Float latitude,
                                   @QueryParam("radius") Long radius) {
        final Location point = Location.of(longitude, latitude);
        return placeDao.findInRadius(point, radius);
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Place> getAll() {
        return placeDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Place add(@Auth User user, final Place place) { return placeDao.create(place); }
}

