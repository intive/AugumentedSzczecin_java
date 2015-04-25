package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.user.ResetPasswordToken;

import java.util.Optional;

/**
 * Created by Marcin Podlodowski on 4/24/15.
 */
public interface ResetPasswordTokenDao<E extends Identifiable> extends CommonDao<E> {
    
    public Optional<String> read(String token);
    
    public void expire(ResetPasswordToken token);
    
    public void invalidateExpired();

}
