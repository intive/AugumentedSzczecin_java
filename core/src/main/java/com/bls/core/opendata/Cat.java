package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {
    private final Metadata metadata;
    private final String c0;

    @JsonCreator
    public Cat(@JsonProperty("__metadata") final Metadata metadata,
               @JsonProperty("c0") final String c0){
        this.metadata = metadata;
        this.c0 = c0;
    }

    public Metadata getMetadata(){
        return metadata;
    }

    public String getc0(){
        return c0;
    }
}
