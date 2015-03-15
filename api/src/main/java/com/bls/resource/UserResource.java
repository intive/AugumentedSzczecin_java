package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.bls.dao.UserDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

@Singleton
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao userDao;

    @Inject
    public UserResource(@Named("user") UserDao userDao) {
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
    public User<String> add(final User<String> user) throws Exception {
        return userDao.add(user);
    }
    
    @POST
    @Path("/clear")
    @Timed
    @ExceptionMetered
    public void clear() {
        userDao.deleteAll();
    }
}
