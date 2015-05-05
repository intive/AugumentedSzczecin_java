package com.bls.resource;

import com.bls.core.user.User;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//import javax.validation.constraints.Min;
/**
 * Search entities by geo location for logged in user.
 */
@Singleton
@Path("/q")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final SearchService searchService;

    @Inject
    public SearchResource(final SearchService searchService) {
        this.searchService = searchService;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public SearchingResults getByRegion(@Auth(required = false)User user, @BeanParam @Valid SearchCriteria searchCriteria) {
        // TODO add sorting, batching results...
        return searchService.searchByCriteria( searchCriteria, user );
    }
}
