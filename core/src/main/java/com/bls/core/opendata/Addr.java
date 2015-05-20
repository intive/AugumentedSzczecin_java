package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Addr {
    private final Metadata metadata;
    private final String street;
    private final String city;
    private final String postcode;

    @JsonCreator
    public Addr(@JsonProperty("__metadata") final Metadata metadata,
                @JsonProperty("street") final String street,
                @JsonProperty("city") final String city,
                @JsonProperty("postcode") final String postcode){
        this.metadata = metadata;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
    }

    public Metadata getMetadata(){
        return metadata;
    }

    public String getStreet(){
        return street;
    }

    public String getCity(){
        return city;
    }

    public String getPostcode(){
        return postcode;
    }
}
