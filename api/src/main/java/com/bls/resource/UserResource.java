package com.bls.resource;

import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Marcin Podlodowski on 3/14/15.
 */

@Singleton
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private CommonDao commonDao;

    @Inject
    public UserResource(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @POST
    @Path("/add")
    @Timed
    public User<String> add(final User<String> user) {
        return (User<String>) commonDao.update(user);
    }
    
}
