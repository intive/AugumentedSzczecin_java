package com.augmented.core;


public class Location {

    private Long latitude;

    private Long longitude;

    public Location() {
    }

    public Location(Long latitude, Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
}
