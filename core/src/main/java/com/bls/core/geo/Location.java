package com.bls.core.geo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.jersey.params.LongParam;

/**
 * Location on some map
 */
public class Location {

    @NotNull
    private final Float latitude;
    @NotNull
    private final Float longitude;

    @JsonCreator
    public Location(@JsonProperty("latitude") Float latitude, @JsonProperty("longitude") Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Location of(final Float longitude, final Float latitude) {
        return new Location(latitude, longitude);
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
