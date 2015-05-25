package com.bls.resource;

import java.util.Collection;
import java.util.List;

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
    // FIXME temporary hack to let Windows client get some points
    @Max(1000)
    @NotNull
    private final Long radius;

    private final List<Category> categories;

    private final List<String> tags;

    private final Optional<User<String>> user;

    public SearchCriteria(@QueryParam("lg") final Float longitude,
            @QueryParam("lt") final Float latitude,
            @QueryParam("radius") final Long radius,
            @QueryParam("cat") final List<Category> categories,
            @QueryParam("tag") final List<String> tags,
            @Auth(required = false) final User user) {
        this.location = new Location(longitude, latitude);
        this.radius = radius;
        this.categories = categories;
        this.tags = tags;
        this.user = Optional.fromNullable(user);
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
}
