package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Owner {

    private final String name;
    private final String email;
    private final String phoneNumber;

    @JsonCreator
    public Owner(@JsonProperty("name") final String name,
                 @JsonProperty("email") final String email,
                 @JsonProperty("phoneNumber") final String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
