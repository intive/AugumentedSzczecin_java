package com.bls.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/pois/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointOfInterestResource {

    private final CommonDao<Poi> poiDao;

    @Inject
    public PointOfInterestResource(@Named("poi") final CommonDao poiDao) {
        this.poiDao = poiDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi getById(@PathParam(value = "id") String id) {
        final Optional<Poi> poiOptional = poiDao.findById(String.valueOf(id));
        if (!poiOptional.isPresent()) {
            throw new NotFoundException(String.format("Point with id: %s not found", id));
        }
        return poiOptional.get();
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void delete(@PathParam(value = "id") String id) {
        poiDao.deleteById(id);
    }
}
