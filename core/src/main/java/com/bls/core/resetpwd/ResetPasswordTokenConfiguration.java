package com.bls.core.resetpwd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;

/**
 *  Configuration used by {@link ResetPasswordToken} 
 */
public class ResetPasswordTokenConfiguration extends Configuration {
    private final int expiration;

    public ResetPasswordTokenConfiguration(@JsonProperty("expiration") final int expiration) {
        this.expiration = expiration;
    }

    @JsonProperty
    public int getExpiration() {
        return expiration;
    }
}
