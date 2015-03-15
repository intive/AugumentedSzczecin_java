package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

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
        userDao.deleteAll();
    }
}
