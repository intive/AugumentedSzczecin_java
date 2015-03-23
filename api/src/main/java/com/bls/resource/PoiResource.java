package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/poi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {

    private static final int POI_COUNT = 5;
    private final CommonDao<Poi> poiDao;
    private final RandomPoiGenerator poiGenerator;

    @Inject
    public PoiResource(@Named("poi") final CommonDao poiDao, final RandomPoiGenerator poiGenerator) {
        this.poiDao = poiDao;
        this.poiGenerator = poiGenerator;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Poi> getAll() {
        return poiDao.findAll();
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

    @Path("/add")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi add(final Poi poi) {
        return poiDao.update(poi);
    }

    @Path("/generate")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Poi> generate() {
        final Collection<Poi> generated = Lists.newArrayListWithCapacity(POI_COUNT);
        poiGenerator.generate(POI_COUNT).forEach(entity -> generated.add(poiDao.create(entity)));
        return generated;
    }
}
