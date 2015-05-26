package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenDataResults {
    private final List<OpenDataPoint> openDataPointList;

    @JsonCreator
    public OpenDataResults(@JsonProperty("results") final List<OpenDataPoint> openDataPointList){
        this.openDataPointList = openDataPointList;
    }

    public List<OpenDataPoint> getOpenDataPointList(){
        return openDataPointList;
    }
}
