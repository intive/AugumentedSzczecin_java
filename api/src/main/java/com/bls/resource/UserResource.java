package com.bls.resource;

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
import com.google.common.base.Optional;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/users/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao<User<String>> userDao;

    @Inject
    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public User getById(@PathParam("id") String id) {
        final Optional<User<String>> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException(String.format("User with id: %s not found", id));
        }
        return user.get();
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

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void removeById(@Auth String foo, @PathParam("id") String id) {
        userDao.deleteById(id);
    }
}
