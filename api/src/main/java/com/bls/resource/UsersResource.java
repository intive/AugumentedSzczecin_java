package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    private final UserDao<User<String>> userDao;

    @Inject
    public UsersResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<User<String>> getAll(@Auth String foo) {
        return userDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public User<String> create(@Valid final User<String> userWithPlaintextPassword) {
        final String hashedPassword = BasicAuthenticator.generateSafeHash(userWithPlaintextPassword.getPassword());
        final User<String> userWithHashedPassword = userWithPlaintextPassword.createUserWithHashedPassword(hashedPassword);
        return userDao.create(userWithHashedPassword);
    }
}
