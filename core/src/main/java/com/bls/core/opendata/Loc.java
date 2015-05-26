package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Loc {
    private final Float lat;
    private final Float lon;

    @JsonCreator
    public Loc(@JsonProperty("lat") final Float lat,
               @JsonProperty("lon") final Float lon){
        this.lat = lat;
        this.lon = lon;
    }

    public Float getLat(){
        return lat;
    }

    public Float getLon(){
        return lon;
    }
}
