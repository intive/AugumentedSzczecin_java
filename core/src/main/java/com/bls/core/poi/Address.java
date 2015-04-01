package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO add javadoc
// TODO add adressType, urls, twitter, facebook...
@JsonInclude(Include.NON_EMPTY)
public class Address {

    private final String country;
    private final String city;
    private final String street;
    private final String mobile;
    private final String phone;
    private final String fax;

    @JsonCreator
    public Address(@JsonProperty("country") final String country,
            @JsonProperty("city") final String city,
            @JsonProperty("street") final String street,
            @JsonProperty("mobile") final String mobile,
            @JsonProperty("phone") final String phone,
            @JsonProperty("fax") final String fax) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.fax = fax;
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }
}
