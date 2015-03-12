package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private final Long latitude;
    private final Long longitude;

    @JsonCreator
    public Location(@JsonProperty("latitude") long latitude, @JsonProperty("longitude") long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public Long getLatitude() {
        return latitude;
    }
}
