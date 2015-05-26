package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cat {
    private final String c0;

    @JsonCreator
    public Cat(@JsonProperty("c0") final String c0){
        this.c0 = c0;
    }

    public String getc0(){
        return c0;
    }
}
