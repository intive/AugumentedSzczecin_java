package com.bls.resource;


import com.bls.core.poi.Event;
import com.bls.core.poi.Location;
import com.bls.dao.EventDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private final EventDao<Event> eventDao;
    private final String description;
    private final Location location;

    public EventResource(EventDao<Event> eventDao, String description, Location location) {
        this.eventDao = eventDao;
        this.description = description;
        this.location = location;
    }

    @Path("/add")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event add(final Event event){ return eventDao.add(event); }

    @Path("/remove")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event remove(final Event event){ return eventDao.add(event); }

    @Path("/update")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event update(final Event event){ return eventDao.add(event); }
}
