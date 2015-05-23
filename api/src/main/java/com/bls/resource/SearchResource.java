package com.bls.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.user.User;
import com.bls.core.views.Views;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Search entities by geo location for logged in user.
 */
@Singleton
@Path("/q")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private final SearchService searchService;

    @Inject
    public SearchResource(final SearchService searchService) {
        this.searchService = searchService;
    }

    private static <T> void validateBean(final T searchCriteria) {
        final Set<ConstraintViolation<T>> constraintViolations = validator.validate(searchCriteria);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    @JsonView(Views.Public.class)
    public SearchingResults getByRegion(@BeanParam SearchCriteria searchCriteria) {
        validateBean(searchCriteria);
        // TODO add sorting, batching results...
        return searchService.searchByCriteria(searchCriteria);
    }
}
