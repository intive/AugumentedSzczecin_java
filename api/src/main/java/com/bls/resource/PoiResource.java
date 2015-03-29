package com.bls.resource;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

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
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bls.core.poi.Poi;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;

@Singleton
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoiResource {

    private static final int POI_COUNT = 5;
    private final CommonDao<Poi> poiDao;
    private final RandomPoiGenerator poiGenerator;
    private final Client openDataClient;
    private final String openDataUrl;

    @Inject
    public PoiResource(@Named("poi") final CommonDao poiDao,
            final RandomPoiGenerator poiGenerator,
            final Client openDataClient,
            @Named("openDataUrl") final String openDataUrl) {
        this.poiDao = poiDao;
        this.poiGenerator = poiGenerator;
        this.openDataClient = openDataClient;
        this.openDataUrl = openDataUrl;
    }

    @Path("/pois")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Poi> getAll() {
        return poiDao.findAll();
    }

    @Path("/poi")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi add(final Poi poi) {
        return poiDao.create(poi);
    }

    @Path("/poi/{id}")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Poi getById(@PathParam(value = "id") String id) {
        return poiDao.findById(String.valueOf(id));
    }

    @Path("/poi/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void delete(@PathParam(value = "id") String id) {
        poiDao.deleteById(id);
    }

    
    /*
        Method temporarily unavailable due to changes in Poi
     */
    /*@Path("/poi/generate")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Poi> generate() {
        final Collection<Poi> generated = Lists.newArrayListWithCapacity(POI_COUNT);
        poiGenerator.generate(POI_COUNT).forEach(entity -> generated.add(poiDao.create(entity)));
        return generated;
    }*/

    @Path("/open")
    @GET
    @Timed
    @ExceptionMetered
    @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
    public Response fetchOpenDataExampleData() {
        // TODO for now return results for hardcoded query for opendata.org.pl
        return openDataClient.
                target(openDataUrl + "/pl_otwarte_zabytki?$format=json&$skip=0&$top=20&$select=id,identification,latitude,longitude")
                .request().buildGet().invoke();
    }
}
