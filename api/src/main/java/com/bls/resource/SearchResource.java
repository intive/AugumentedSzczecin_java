package com.bls.resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import com.bls.core.opendata.OpenData;
import com.bls.core.opendata.OpenDataPoint;
import com.bls.core.place.Place;
import com.bls.core.search.SearchCriteria;
import com.bls.core.search.SearchResults;
import com.bls.core.views.Views;
import com.bls.service.SearchService;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private final Client openDataClient;
    private final String openDataUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    public SearchResource(final SearchService searchService, final Client openDataClient, @Named("openDataUrl") final String openDataUrl) {
        this.searchService = searchService;
        this.openDataClient = openDataClient;
        this.openDataUrl = openDataUrl + "/patronat2015?$format=json";
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
    public SearchResults getByRegion(@BeanParam SearchCriteria searchCriteria) throws IOException {
        boolean shouldGetDataFromOpendataServer = !searchCriteria.getUser().isPresent();
        if (shouldGetDataFromOpendataServer) {
            return getFromOpendata(searchCriteria);
        }

        validateBean(searchCriteria);

        // TODO add sorting
        return searchService.searchByCriteria(searchCriteria);
    }

    // FIXME search criteria is not used
    private SearchResults getFromOpendata(final SearchCriteria searchCriteria) throws IOException {
        OpenData openData = openDataClient.target(openDataUrl).request(MediaType.APPLICATION_JSON).get().readEntity(OpenData.class);

        List<OpenDataPoint> openDataPointList = openData.getOpenDataResults().getOpenDataPointList();

        List<Place> places = Arrays.asList(objectMapper.readValue(objectMapper.writeValueAsString(openDataPointList), Place[].class));
        SearchResults searchResults = new SearchResults();
        searchResults.putPlaces(places);
        return searchResults;
    }
}
