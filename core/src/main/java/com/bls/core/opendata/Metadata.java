package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;

public class Metadata {
    private final String type;

    @Nullable
    private final String id;

    @Nullable
    private final String uri;

    @JsonCreator
    public Metadata(@JsonProperty("type") final String type,
                    @JsonProperty("id") final String id,
                    @JsonProperty("uri") final String uri){
        this.type = type;
        this.id = id;
        this.uri = uri;
    }

    public String getType(){
        return type;
    }

    public String getId(){
        return id;
    }

    public String getUri(){
        return uri;
    }
}
