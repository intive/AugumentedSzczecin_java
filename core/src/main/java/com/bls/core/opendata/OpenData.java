package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenData {

    private final OpenDataResults openDataResults;

    @JsonCreator
    public OpenData(@JsonProperty("d") final OpenDataResults openDataResults){
        this.openDataResults = openDataResults;
    }

    public OpenDataResults getOpenDataResults(){
        return openDataResults;
    }
}
