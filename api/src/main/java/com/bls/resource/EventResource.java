package com.bls.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.event.Event;
import com.bls.core.user.User;
import com.bls.dao.EventDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/events/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private final EventDao<Event> eventDao;

    @Inject
    public EventResource(final EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event get(@Auth User user, @PathParam("id") final String id) {
        return eventDao.findByIdSafe(user, id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event update(@Auth User user, @PathParam("id") final String id, @Valid final Event event) {
        return eventDao.updateSafe(user, id, event);
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) {
        eventDao.deleteById((String)eventDao.findByIdSafe(user, id).getId());
    }

}
