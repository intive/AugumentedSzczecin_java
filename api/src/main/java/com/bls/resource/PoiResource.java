package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

@Singleton
@Path("/poi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {

    private static final int POI_COUNT = 5;
    private final CommonDao<Poi<String>, String> poiDao;
    private final RandomPoiGenerator poiGenerator;

    @Inject
    public PoiResource(@Named("poi") final CommonDao poiDao, final RandomPoiGenerator poiGenerator) {
        this.poiDao = poiDao;
        this.poiGenerator = poiGenerator;
    }

    @GET
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
        final Collection<Poi<String>> generated = Lists.newArrayListWithCapacity(POI_COUNT);
        poiGenerator.generate(POI_COUNT).forEach(entity -> generated.add(poiDao.create(entity)));
        return generated;
    }
}
