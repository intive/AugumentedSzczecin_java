package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Loc {
    private final Metadata metadata;
    private final Float lat;
    private final Float lon;

    @JsonCreator
    public Loc(@JsonProperty("__metadata") final Metadata metadata,
               @JsonProperty("lat") final Float lat,
               @JsonProperty("lon") final Float lon){
        this.metadata = metadata;
        this.lat = lat;
        this.lon = lon;
    }

    public Metadata getMetadata(){
        return metadata;
    }

    public Float getLat(){
        return lat;
    }

    public Float getLon(){
        return lon;
    }
}
