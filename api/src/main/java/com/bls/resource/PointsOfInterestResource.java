package com.bls.resource;

import com.bls.core.geo.Location;
import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.bls.dao.CommonDao;
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
@Path("/pois")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PointsOfInterestResource {

    private final CommonDao<Poi> poiDao;

    @Inject
    public PointsOfInterestResource(final CommonDao poiDao) {
        this.poiDao = poiDao;
    }

    @Path("/q")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Poi> getByRegion(@Auth User user, @QueryParam("lg") Float longitude,
                                       @QueryParam("lt") Float latitude,
                                       @QueryParam("radius") Long radius) {
        final Location point = Location.of(longitude, latitude);
        return poiDao.findInRadius(point, radius);
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Poi> getAll() {
        return poiDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi add(final Poi poi) {
        return poiDao.create(poi);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi modify(final Poi poi) {
        return poiDao.update(poi);
    }
}
