package com.bls.resource;

import com.bls.core.place_monument.PlaceMonument;
import com.bls.core.user.User;
import com.bls.dao.PlaceMonumentDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/places/monuments/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceMonumentResource {

    private final PlaceMonumentDao<PlaceMonument> placeMonumentDao;

    @Inject
    public PlaceMonumentResource(final PlaceMonumentDao placeMonumentDao) {
        this.placeMonumentDao = placeMonumentDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public PlaceMonument get(@PathParam("id") final String id) {
        return getPlaceMonumentSafe(id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public PlaceMonument update(@Auth User user, @PathParam("id") final String id) {
        return placeMonumentDao.update(getPlaceMonumentSafe(id));
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) { placeMonumentDao.deleteById(id); }

    private PlaceMonument getPlaceMonumentSafe(final String id) {
        final Optional<PlaceMonument> placeMonument = placeMonumentDao.findById(id);
        if (!placeMonument.isPresent()) {
            throw new NotFoundException(String.format("Place with id: %s not found", id));
        }
        return placeMonument.get();
    }
}