package com.bls.core.search;

import com.bls.core.geo.Location;
import com.bls.core.place.Place;
import com.bls.core.poi.Category;
import com.bls.core.user.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import java.util.Collection;
import java.util.List;

public class SearchCriteria {

    @NotNull
    private final Location location;
    @Min(0)
    @NotNull
    private final Long radius;
    private final Collection<Category> categories;
    private final Collection<String> tags;
    private final Optional<String> name;
    private final Optional<String> street;
    private final List<Place.Subcategory> subcat;
    private final Optional<Boolean> paid;
    private final Optional<Boolean> open;
    private final Optional<User> user;
    private final Optional<Integer> page;
    private final Integer pageSize;

    @Inject
    public SearchCriteria(
            // authorization
            @Auth(required = false) final User user,
            // configuration
            @Named("defaultPageSize") final Integer defaultPageSize,
            // request parameters
            @QueryParam("lg") final Float longitude,
            @QueryParam("lt") final Float latitude,
            @QueryParam("radius") final Long radius,
            @QueryParam("cat") final List<Category> categories,
            @QueryParam("tag") final List<String> tags,
            @QueryParam("name") final String name,
            @QueryParam("street") final String street,
            @QueryParam("subcat") final List<Place.Subcategory> subcat,
            @QueryParam("paid") final Boolean paid,
            @QueryParam("open") final Boolean open,
            @QueryParam("page") final Integer page,
            @QueryParam("pageSize") final Integer queriedPageSize) {
        this.location = new Location(longitude, latitude);
        this.radius = radius;
        this.categories = categories;
        this.tags = tags;
        this.name = Optional.fromNullable(name);
        this.street = Optional.fromNullable(street);
        this.subcat = subcat;
        this.paid = Optional.fromNullable(paid);
        this.open = Optional.fromNullable(open);
        this.user = Optional.fromNullable(user);
        this.page = Optional.fromNullable(page);
        this.pageSize = queriedPageSize != null ? queriedPageSize : defaultPageSize;
    }

    public Optional<User> getUser() {
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

    public Integer getPageSize() {
        return pageSize;
    }

    public Optional<String> getName(){
        return name;
    }

    public Optional<String> getStreet(){
        return street;
    }

    public List<Place.Subcategory> getSubcat(){
        return subcat;
    }

    public Optional<Boolean> getPaid(){
        return paid;
    }

    public Optional<Boolean> getOpen(){
        return open;
    }
}
