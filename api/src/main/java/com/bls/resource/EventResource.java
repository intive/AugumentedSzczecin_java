package com.bls.resource;


import com.bls.core.poi.Event;
import com.bls.core.poi.Location;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private final CommonDao<Event> eventDao;
    private final String description;
    private final Location location;

    public EventResource(CommonDao<Event> eventDao, String description, Location location) {
        this.eventDao = eventDao;
        this.description = description;
        this.location = location;
    }


    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event add(final Event event){ return eventDao.create(event); }

    @Path("/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void delete(final Event event){ eventDao.delete(event); }

    @Path("/update")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event update(final Event event){ return eventDao.update(event); }
}
