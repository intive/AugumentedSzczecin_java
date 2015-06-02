package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.user.User;

public interface PoiDao<E extends Identifiable> extends CommonDao<E> {
    E createWithOwner(E entity, User user);
}
