package com.bls.resource;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
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
@Path("/whoami")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WhoAmIResource {
    private final UserDao<User<String>> userDao;

    @Inject
    public WhoAmIResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public User getUser(@Auth User userCredentials) {
        final Optional<User<String>> user = userDao.findByEmail(userCredentials.getEmail());
        if (!user.isPresent()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }
}
