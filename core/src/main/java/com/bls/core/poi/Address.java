package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *  An address of a POI
 */
@JsonInclude(Include.NON_EMPTY)
public class Address {

    @NotBlank
    @Size(min=1, max=500)
    @Pattern(regexp = "^[\\p{L}0-9 ]+$")
    private final String street;
    @NotBlank
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private final String zipcode;
    @NotBlank
    @Size(min=1, max=30)
    @Pattern(regexp = "^[\\p{L}0-9 ]+$")
    private final String city;
    @NotBlank
    @Size(min=1, max=10)
    private final String streetNumber;
    @Size(min=1, max=5)
    private final String houseNumber;

    @JsonCreator
    public Address(@JsonProperty("city") final String city,
                   @JsonProperty("street") final String street,
                   @JsonProperty("zipcode") final String zipcode, 
                   @JsonProperty("streetNumber") final String streetNumber, 
                   @JsonProperty("houseNumber") final String houseNumber) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.streetNumber = streetNumber;
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
}
