package com.bls.core.opendata;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Addr {
    private final String street;
    private final String city;
    private final String postcode;

    @JsonCreator
    public Addr(@JsonProperty("street") final String street,
                @JsonProperty("city") final String city,
                @JsonProperty("postcode") final String postcode){
        this.street = street;
        this.city = city;
        this.postcode = postcode;
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
