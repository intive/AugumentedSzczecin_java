package com.bls.resource;

import com.bls.core.event.Event;
import com.bls.core.geo.Location;
import com.bls.core.user.User;
import com.bls.dao.EventDao;
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
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {

    private final EventDao<Event> eventDao;

    @Inject
    public EventsResource(final EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Event> getAll() {
        return eventDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event add(final Event event) { return eventDao.create(event); }

    @Path("/q")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Event> getByRegion(@Auth User user, @QueryParam("lg") Float longitude,
                                       @QueryParam("lt") Float latitude,
                                       @QueryParam("radius") Long radius) {
        final Location point = Location.of(longitude, latitude);
        return eventDao.findInRadius(point, radius);
    }
}
