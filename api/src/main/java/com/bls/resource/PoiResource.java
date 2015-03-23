package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
<<<<<<< HEAD
import javax.ws.rs.*;
=======
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
>>>>>>> master
import javax.ws.rs.core.MediaType;

import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
<<<<<<< HEAD
=======
import com.google.common.collect.Lists;

import io.dropwizard.hibernate.UnitOfWork;
>>>>>>> master

@Singleton
@Path("/poi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {

    private static final int POI_COUNT = 5;
<<<<<<< HEAD
    private final CommonDao<Poi<String>, String> poiDao;
=======
    private final CommonDao<Poi> poiDao;
>>>>>>> master
    private final RandomPoiGenerator poiGenerator;

    @Inject
    public PoiResource(@Named("poi") final CommonDao poiDao, final RandomPoiGenerator poiGenerator) {
        this.poiDao = poiDao;
        this.poiGenerator = poiGenerator;
    }

    @GET
<<<<<<< HEAD
    @Timed
    @ExceptionMetered
    public Collection<Poi<String>> getAll() {
        return poiDao.findAll();
    }

    @POST
    @Path("/add")
    @Timed
    @ExceptionMetered
    public Poi<String> add(final Poi<String> poi) {
        return poiDao.update(poi);
    }

    @GET
    @Path("/generate")
    @Timed
    @ExceptionMetered
    public Collection<Poi<String>> generate() {
        final Collection<Poi<String>> poiCollection = poiGenerator.generate(POI_COUNT);
        poiCollection.forEach(poiDao::create);
        return poiCollection;
=======
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
>>>>>>> master
    }
}
