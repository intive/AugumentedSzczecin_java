package com.bls.core.resetpwd;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Random;

/**
 *  A token needed to change user password.
 *  
 *  @param <K> Key type for this entity
 */
public class ResetPasswordToken<K> extends IdentifiableEntity<K> {

    @NotNull
    private final DateTime expiryDate;

    private final String token;

    @JsonCreator
    public ResetPasswordToken(@JsonProperty("id") final K id,
                              @JsonProperty("expiryDate") final DateTime expiryDate,
                              @JsonProperty("token") final String token) {
        super(id);
        this.expiryDate = expiryDate;
        this.token = token;
    }
    
    public ResetPasswordToken(ResetPasswordTokenConfiguration tokenConfig) {
        super(null);
        this.expiryDate = DateTime.now().plusMinutes(tokenConfig.getExpiration());
        this.token = generateTokenString();
    }
    
    public DateTime getExpiryDate() {
        return expiryDate;
    }

//    @JsonIgnore
    public String getToken() {
        return token;
    }
    
    public boolean checkExpired() {
        return this.getExpiryDate().isBefore(DateTime.now());
    }
    
    private String generateTokenString() {
        return new BigInteger(130, new Random()).toString(32);
    }
    
}