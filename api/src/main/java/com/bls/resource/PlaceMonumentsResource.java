package com.bls.resource;

import com.bls.core.place_monument.PlaceMonument;
import com.bls.dao.PlaceMonumentDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/places/monuments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlaceMonumentsResource {

    private final PlaceMonumentDao<PlaceMonument> placeMonumentDao;

    @Inject
    public PlaceMonumentsResource(final PlaceMonumentDao placeMonumentDao) {
        this.placeMonumentDao = placeMonumentDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<PlaceMonument> getAll() {
        return placeMonumentDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public PlaceMonument add(final PlaceMonument placeMonument) { return placeMonumentDao.create(placeMonument); }
}
