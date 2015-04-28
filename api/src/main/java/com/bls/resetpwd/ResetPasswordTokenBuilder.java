package com.bls.resetpwd;

import com.bls.core.user.ResetPasswordToken;
import org.bson.types.ObjectId;

import java.util.Calendar;

/**
 * Created by Marcin Podlodowski on 28.04.15.
 */
public class ResetPasswordTokenBuilder {
    // FIXME move to ResetPasswordToken (first fix org.bson.types.ObjectId dependency in core package)
    public ResetPasswordToken forDuration(int minutes) {
        String token = TokenGenerator.getNew();
        Calendar cal = Calendar.getInstance();
        final long now = cal.getTimeInMillis();
        final long expiry = now + 1000 * 60 * minutes;
        return new ResetPasswordToken(new ObjectId(), expiry, token);
    }

}
