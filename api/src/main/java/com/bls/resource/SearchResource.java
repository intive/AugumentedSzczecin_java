package com.bls.resource;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
//import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
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
    public SearchingResults getByRegion(@Auth(required = false) @BeanParam @Valid SearchCriteria searchCriteria) {
        // TODO add sorting, batching results...
        return searchService.searchByCriteria( searchCriteria );
    }
}
