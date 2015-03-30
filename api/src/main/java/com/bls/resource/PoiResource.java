package com.bls.resource;

import java.util.Collection;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.bls.core.geo.Location;
import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Singleton
@Path("/pois")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {

    private final CommonDao<Poi> poiDao;

    @Inject
    public PoiResource(@Named("poi") final CommonDao poiDao) {
        this.poiDao = poiDao;
    }

    @Path("/q")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Stream<Poi> getByRegion(@QueryParam("lg") Float longitude,
            @QueryParam("lt") Float latitude,
            @QueryParam("radius") LongParam radius) {
        final Location point = Location.of(longitude, latitude);
        return poiDao.findAll().stream().filter(poi -> poi.getLocation().isInRadius(point, radius));
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

    @Path("/{id}")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi getById(@PathParam(value = "id") String id) {
        return poiDao.findById(String.valueOf(id));
    }

    @Path("/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void delete(@PathParam(value = "id") String id) {
        poiDao.deleteById(id);
    }
}
