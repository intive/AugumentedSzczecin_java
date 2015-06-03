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
import javax.validation.Valid;
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
        return commercialDao.findByIdSafe(user, id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Commercial update(@Auth User user, @PathParam("id") final String id, @Valid final Commercial commercial) {
        return commercialDao.updateSafe(user, id, commercial);
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) {
        commercialDao.deleteById((String)commercialDao.findByIdSafe(user, id).getId());
    }

}