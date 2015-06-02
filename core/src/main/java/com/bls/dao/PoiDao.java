package com.bls.dao;

import com.bls.core.poi.Poi;
import com.bls.core.user.User;

public interface PoiDao<P extends Poi> extends CommonDao<P> {

    default P createWithOwner(P entity, User user) {
        entity.setOwner(user);
        return create(entity);
    }
}
