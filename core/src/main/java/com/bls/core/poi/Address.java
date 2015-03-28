package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    private final String country;
    private final String city;
    private final String street;
    private final String fax;

    @JsonCreator
    public Address(@JsonProperty("country") final String country,
                   @JsonProperty("city") final String city,
                   @JsonProperty("street") final String street,
                   @JsonProperty("fax") final String fax) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.fax = fax;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getFax() {
        return this.fax;
    }
}
