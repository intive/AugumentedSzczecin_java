package com.bls.resource;


import com.bls.core.commercial.Commercial;
import com.bls.core.user.User;
import com.bls.dao.CommercialDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/commercials/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommercialResource {

    private final CommercialDao<Commercial> commercialDao;

    @Inject
    public CommercialResource(final CommercialDao commercialDao) {
        this.commercialDao = commercialDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Commercial get(@Auth User user, @PathParam("id") final String id) {
        return getCommercialSafe(id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Commercial update(@Auth User user, @PathParam("id") final String id) {
        return commercialDao.update(getCommercialSafe(id));
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) { commercialDao.deleteById(id); }

    private Commercial getCommercialSafe(final String id) {
        final Optional<Commercial> commercial = commercialDao.findById(id);
        if (!commercial.isPresent()) {
            throw new NotFoundException(String.format("Commercial with id: %s not found", id));
        }
        return commercial.get();
    }
}