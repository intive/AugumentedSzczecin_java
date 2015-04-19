package com.bls.core.user;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * Created by Marcin Podlodowski on 4/24/15.
 */
public class ResetPasswordToken<K> extends IdentifiableEntity<K> {
    
    @NotNull
    private final long expiryDate;
    private final String token;

    @JsonCreator
    public ResetPasswordToken(@JsonProperty("id") final K id,
                              @JsonProperty("expiryDate") final long expiryDate,
                              @JsonProperty("token") final String token) {
        super(id);
        this.expiryDate = expiryDate;
        this.token = token;
    }
    
    public long getExpiryDate() {
        return expiryDate;
    }

    public String getToken() {
        return token;
    }
    
    public boolean checkExpired() {
        return this.getExpiryDate() <= Calendar.getInstance().getTimeInMillis();
    }
}