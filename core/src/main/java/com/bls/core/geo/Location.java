package com.bls.core.geo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jersey.params.LongParam;

import javax.validation.constraints.NotNull;

/**
 * Location on some map
 */
@JsonInclude(Include.NON_EMPTY)
public class Location {

    @NotNull
    private final Float longitude;
    @NotNull
    private final Float latitude;


    @JsonCreator
    public Location(@JsonProperty("longitude") Float longitude, @JsonProperty("latitude") Float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Location of(final Float longitude, final Float latitude) {
        return new Location(longitude, latitude);
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public boolean isInRadius(final Location point, final LongParam radius) {
        // TODO implement me
        return true;
    }
}
