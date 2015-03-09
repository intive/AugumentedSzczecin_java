package com.augmented.resources;

import com.augmented.core.Location;
import com.augmented.core.Poi;
import com.augmented.core.PoiFactory;
import com.augmented.core.Tag;
import com.augmented.dao.PoiDao;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Singleton
@Path("/poi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PoisResource {
    private final Logger logger = LoggerFactory.getLogger(PoisResource.class);
    private final PoiDao poiDao;
    private final PoiFactory poiFactory;

    @Inject
    public PoisResource(final PoiDao poiDao, final PoiFactory poiFactory) {
        this.poiDao = poiDao;
        this.poiFactory = poiFactory;
        poiDao.clearAll();
    }

    @GET
    @Timed
    public List<Poi> sayHello(@Context HttpHeaders ctx) {
        logger.info("User-Agent: " + ctx.getRequestHeader("User-Agent"));
        logger.info(Integer.toString(ctx.hashCode()));
        return poiDao.findAllPoi();
    }

    @POST
    @Path("/add")
    @Timed
    public Response add(final Poi poi) {
        poiDao.savePoi(poi);
        return Response.ok("Ok!").build();
    }


    //------- Thug Life... -------
    @GET
    @Path("/generate")
    @Timed
    public List<String> generate() {
        final List<String> ids = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Poi poi = poiFactory.createNewInstance();
            poi.setName("thug_" + new Random().nextInt(1000));
            final Tag[] tags = Tag.values();
            poi.setTag(Lists.newArrayList(tags).get(new Random().nextInt(tags.length)));
            Location location = new Location();
            location.setLatitude(new Random().nextLong());
            location.setLongitude(new Random().nextLong());
            poi.setLocation(location);
            ids.add(poiDao.savePoi(poi));
        }
        return ids;
    }

}
