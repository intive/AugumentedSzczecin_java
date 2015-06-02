package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.place.Place;
import com.bls.core.user.User;
import com.bls.dao.PlaceDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

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

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Place> getAll(@Auth User user) {
        return placeDao.findAll(user);
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Place add(@Auth User user, @Valid final Place place) { return placeDao.createWithOwner(place, user); }
}

