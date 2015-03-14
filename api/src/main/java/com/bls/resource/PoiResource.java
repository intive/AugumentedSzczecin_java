package com.bls.resource;

import java.util.Collection;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

@Singleton
@Path("/poi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {

    private static final int POI_COUNT = 5;

    private final CommonDao<Poi<String>, String> commonDao;
    private final RandomPoiGenerator poiGenerator;

    @Inject
    public PoiResource(final CommonDao commonDao, final RandomPoiGenerator poiGenerator) {
        this.commonDao = commonDao;
        this.poiGenerator = poiGenerator;
    }

    @GET
    @Timed
    public Collection<Poi<String>> getAll() {
        return commonDao.findAll();
    }

    @POST
    @Path("/add")
    @Timed
    public Poi<String> add(final Poi<String> poi) {
        return commonDao.update(poi);
    }

    @GET
    @Path("/generate")
    @Timed
    public Collection<Poi<String>> generate() {
        final Collection<Poi<String>> poiCollection = poiGenerator.generate(POI_COUNT);
        for (Poi<String> randomPoi : poiCollection) {
            commonDao.create(randomPoi);
        }
        return poiCollection;
    }
}
