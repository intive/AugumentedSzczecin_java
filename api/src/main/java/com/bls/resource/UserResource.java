package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao<User<String>, String> userDao;

    @Inject
    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Timed
    @ExceptionMetered
    @UnitOfWork
    public Collection<User<String>> get(@Auth String foo) {
        return userDao.findAll();
    }

    @POST
    @Path("/add")
    @Timed
    @ExceptionMetered
    @UnitOfWork
    public User<String> create(@Valid final User<String> userWithPailtextPassword) {
        final String hashedPassword = BasicAuthenticator.generateSafeHash(userWithPailtextPassword.getPassword());
        final User<String> userWithHashedPassword = userWithPailtextPassword.createUserWithHashedPassword(hashedPassword);
        return userDao.create(userWithHashedPassword);
    }

    @POST
    @Path("/clear")
    @Timed
    @ExceptionMetered
    @UnitOfWork
    public void removeAll(@Auth String foo) {
        userDao.deleteAll();
    }
}
