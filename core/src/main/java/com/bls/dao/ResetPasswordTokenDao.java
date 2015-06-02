package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.resetpwd.ResetPasswordToken;
import com.google.common.base.Optional;

public interface ResetPasswordTokenDao<E extends Identifiable> extends CommonDao<E> {

    Optional<ResetPasswordToken> read(String token);

    void expire(ResetPasswordToken token);

    void invalidateExpired();
}
