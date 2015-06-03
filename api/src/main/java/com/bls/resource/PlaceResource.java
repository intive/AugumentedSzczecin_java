package com.bls.resource;

import com.bls.core.user.User;
import com.bls.dao.PlaceDao;
import com.bls.core.place.Place;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/places/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceResource {

    private final PlaceDao<Place> placeDao;

    @Inject
    public PlaceResource(final PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Place get(@Auth User user, @PathParam("id") final String id) {
        return placeDao.findByIdSafe(user, id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Place update(@Auth final User user, @PathParam("id") final String id, @Valid final Place place) {
        return placeDao.updateSafe(user, id, place);
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) {
        placeDao.deleteById((String)placeDao.findByIdSafe(user, id).getId());
    }
}
