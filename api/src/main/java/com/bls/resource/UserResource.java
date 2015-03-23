package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
<<<<<<< HEAD
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

=======
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.auth.basic.BasicAuthenticator;
>>>>>>> master
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

<<<<<<< HEAD
=======
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import static com.google.common.base.Preconditions.checkState;

>>>>>>> master
@Singleton
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

<<<<<<< HEAD
    private final UserDao<User<String>, String> userDao;
=======
    private final UserDao<User<String>> userDao;
>>>>>>> master

    @Inject
    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

<<<<<<< HEAD
    @GET
    @Timed
    @ExceptionMetered
    public Collection<User<String>> get() {
        return userDao.findAll();
    }

    @POST
    @Path("/add")
    @Timed
    @ExceptionMetered
    public User<String> create(final User<String> user) {
        return userDao.create(user);
    }

    @POST
    @Path("/clear")
    @Timed
    @ExceptionMetered
    public void removeAll() {
=======
    @Path("/list")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<User<String>> getAll(@Auth String foo) {
        return userDao.findAll();
    }

    @Path("/add")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public User<String> create(@Valid final User<String> userWithPlaintextPassword) {

        final String hashedPassword = BasicAuthenticator.generateSafeHash(userWithPlaintextPassword.getPassword());
        final User<String> userWithHashedPassword = userWithPlaintextPassword.createUserWithHashedPassword(hashedPassword);
        checkState(userDao.findByEmail(userWithPlaintextPassword.getEmail()).isPresent(), "User wih email: %s exists",
                userWithPlaintextPassword.getEmail());
        return userDao.create(userWithHashedPassword);
    }

    @Path("/clear")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void removeAll(@Auth String foo) {
>>>>>>> master
        userDao.deleteAll();
    }
}
