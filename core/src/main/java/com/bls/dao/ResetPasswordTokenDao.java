package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.resetpwd.ResetPasswordToken;

/**
 * Created by Marcin Podlodowski on 4/24/15.
 */
public interface ResetPasswordTokenDao<E extends Identifiable> extends CommonDao<E> {
    
    com.google.common.base.Optional<ResetPasswordToken> read(String token);
    
    void expire(ResetPasswordToken token);
    
    void invalidateExpired();

}
