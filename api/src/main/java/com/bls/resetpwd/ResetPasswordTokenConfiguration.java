package com.bls.resetpwd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;

/**
 * Created by Marcin Podlodowski on 28.04.15.
 */
public class ResetPasswordTokenConfiguration extends Configuration {
    @Valid
    private final int expiration;

    public ResetPasswordTokenConfiguration(@JsonProperty("expiration") final int expiration) {
        this.expiration = expiration;
    }

    @JsonProperty
    public int getExpiration() {
        return expiration;
    }
}
