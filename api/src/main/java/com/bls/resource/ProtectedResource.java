package com.bls.resource;

import com.bls.core.user.User;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.auth.Auth;
        

/**
 * Created by Marcin Podlodowski on 3/14/15.
 */

@Singleton
@Path("/auth")
@Produces(MediaType.TEXT_PLAIN)
public class ProtectedResource {

    @GET
    @Timed
    public String showProtected(@Auth User user) {
        return "Protected content";
    }
    
    
}
