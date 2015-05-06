package com.bls.resource;

import com.bls.core.geo.Location;
import com.bls.core.poi.Category;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Lists;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.Min;
import javax.ws.rs.QueryParam;

public class SearchCriteria {

    @NotNull
    private final Location location;
    @Min(0)
    @Max(360)
    @NotNull
    private final Long radius;
    private final List<Category> categories;
    private final List<String> tags;

    public SearchCriteria(@QueryParam("lg") final Float longitude,
                          @QueryParam("lt") final Float latitude,
                          @QueryParam("radius") final Long radius,
                          @QueryParam("cat") final List<Category> categories,
                          @QueryParam("tags") final List<String> tags) {
        this.location = new Location(longitude, latitude);
        this.radius = radius;
        this.categories = categories;
        this.tags = tags;
    }

    public Location getLocation() {
        return location;
    }

    public Long getRadius() {
        return radius;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public Collection<String> getTags() {
        return tags;
    }
}
