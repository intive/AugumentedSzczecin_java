package com.bls.core.user;

import com.bls.core.IdentifiableEntity;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

/**
 * Created by Marcin Podlodowski on 4/24/15.
 */
public class ResetPasswordToken<K> extends IdentifiableEntity<K> {
    // TODO move to dw config file
    private static final long DURATION = 30;
    
    @NotNull
    private final LocalDateTime expiryDate;
    private final String token;

    public ResetPasswordToken() {
        super((K) new ObjectId());
//        this.expiryDate = LocalDateTime.now().plusMinutes(DURATION);
        // FIXME Jackson can't map this
        this.expiryDate = null;
        this.token = TokenGenerator.getNew();
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public String getToken() {
        return token;
    }
    
    public static ResetPasswordToken generate() {
        return new ResetPasswordToken();
    }
    
    public boolean checkExpired() {
        return this.getExpiryDate().compareTo(LocalDateTime.now()) < 0;
    }
}

class TokenGenerator {
    public static String getNew() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
