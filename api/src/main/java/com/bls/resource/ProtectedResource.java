package com.bls.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.user.User;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Singleton;

import io.dropwizard.auth.Auth;

@Singleton
@Path("/auth")
@Produces(MediaType.TEXT_PLAIN)
public class ProtectedResource {

    @GET
    @Timed
    @ExceptionMetered
    public String showProtected(@Auth User user) {
        return "Protected content";
    }
}
