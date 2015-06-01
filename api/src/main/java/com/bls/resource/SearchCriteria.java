package com.bls.resource;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import com.bls.core.geo.Location;
import com.bls.core.poi.Category;
import com.bls.core.user.User;
import com.google.common.base.Optional;

import io.dropwizard.auth.Auth;

public class SearchCriteria {

    @NotNull
    private final Location location;

    @Min(0)
    @NotNull
    private final Long radius;

    private final List<Category> categories;

    private final List<String> tags;

    private final Optional<User<String>> user;
    
    private final Optional<Integer> page;
    
    private final Optional<Integer> pageSize;

    @Inject
    public SearchCriteria(@QueryParam("lg") final Float longitude,
            @QueryParam("lt") final Float latitude,
            @QueryParam("radius") final Long radius,
            @QueryParam("cat") final List<Category> categories,
            @QueryParam("tag") final List<String> tags,
            @QueryParam("page") final Integer page,
            @Auth(required = false) final User user,
            @Named("pageSize") final Integer pageSize) {
        this.location = new Location(longitude, latitude);
        this.radius = radius;
        this.categories = categories;
        this.tags = tags;
        this.user = Optional.fromNullable(user);
        this.page = Optional.fromNullable(page);
        this.pageSize = Optional.of(pageSize);
    }

    public Optional<User<String>> getUser() {
        return user;
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

    public Optional<Integer> getPage() {
        return page;
    }

    public Optional<Integer> getPageSize() {
        return pageSize;
    }
}
