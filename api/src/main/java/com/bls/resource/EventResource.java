package com.bls.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
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
import com.bls.dao.CommonDao;
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

    private final CommonDao<Event> eventDao;

    @Inject
    public EventResource(@Named("event") final CommonDao eventDao) {
        this.eventDao = eventDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event get(@Auth User user, @PathParam("id") final String id) {
        return getEventSafe(id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Event update(@Auth User user, @PathParam("id") final String id) {
        return eventDao.update(getEventSafe(id));
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) { eventDao.deleteById(id); }

    private Event getEventSafe(final String id) {
        final Optional<Event> event = eventDao.findById(id);
        if (!event.isPresent()) {
            throw new NotFoundException(String.format("Event with id: %s not found", id));
        }
        return event.get();
    }
}
