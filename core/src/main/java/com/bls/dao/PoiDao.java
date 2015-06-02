package com.bls.dao;

import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.google.common.base.Optional;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

public interface PoiDao<P extends Poi> extends CommonDao<P> {

    default P createWithOwner(final P entity, final User user) {
        entity.setOwner(user);
        return create(entity);
    }

    default P findByIdSafe(final User user, final String id) {
        final Optional<P> entity = findById(id);
        if (!entity.isPresent()) {
            throw new ConstraintViolationException(String.format("Poi with id: %s not found", id), Collections.emptySet());
        }
        if (!entity.get().getOwner().getId().equals(user.getId())) {
            throw new ConstraintViolationException("Access denied", Collections.emptySet());
        }
        return entity.get();
    }

    default P updateSafe(final User user, final String id, final P entity) {
        entity.setId(findByIdSafe(user, id).getId());
        entity.setOwner(user);
        return update(entity);
    }
}
