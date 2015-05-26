package com.bls.resource;

import com.bls.core.opendata.OpenData;
import com.bls.core.opendata.OpenDataPoint;
import com.bls.core.place.Place;
import com.bls.core.views.Views;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    private final Client openDataClient;

    private final String openDataUrl;

    @Inject
    public SearchResource(final SearchService searchService, @Named("openDataUrl") final Client openDataClient, final String openDataUrl) {
        this.searchService = searchService;
        this.openDataClient = openDataClient;
        this.openDataUrl = openDataUrl;
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
    public SearchingResults getByRegion(@BeanParam SearchCriteria searchCriteria) throws IOException {
        if (!searchCriteria.getUser().isPresent()){
            OpenData openData = openDataClient
                    .target(openDataUrl + "/patronat2015?$format=json")
                    .request(MediaType.APPLICATION_JSON).get().readEntity(OpenData.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<OpenDataPoint> openDataPointList = openData.getOpenDataResults().getOpenDataPointList();

            List<Place> places = Arrays.asList(objectMapper.readValue(objectMapper.writeValueAsString(openDataPointList), Place[].class));
            SearchingResults searchingResults = new SearchingResults();
            searchingResults.putPlaces(places);
            return searchingResults;
        }
        else {
            validateBean(searchCriteria);
            // TODO add sorting, batching results...
            return searchService.searchByCriteria(searchCriteria);
        }
    }
}
