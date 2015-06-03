package com.bls.resource;


import com.bls.core.commercial.Commercial;
import com.bls.core.user.User;
import com.bls.dao.CommercialDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Singleton
@Path("/commercials")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommercialsResource {

    private final CommercialDao<Commercial> commercialDao;

    @Inject
    public CommercialsResource(final CommercialDao commercialDao) {
        this.commercialDao = commercialDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Commercial> getAll(@Auth User user) {
        return commercialDao.findAll(user);
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Commercial add(@Auth User user, @Valid final Commercial commercial) {
        return commercialDao.createWithOwner(commercial, user);
    }
}
