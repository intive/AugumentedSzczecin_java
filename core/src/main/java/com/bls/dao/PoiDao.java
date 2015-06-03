package com.bls.dao;

import javax.validation.ValidationException;

import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.google.common.base.Optional;

public interface PoiDao<P extends Poi> extends CommonDao<P> {

    default P createWithOwner(final P entity, final User user) {
        entity.setOwner(user);
        return create(entity);
    }

    default P findByIdSafe(final User user, final String id) {
        final Optional<P> entity = findById(id);

        if (!entity.isPresent()) {
            // TODO security: hide core problem
            throw new ValidationException(String.format("Poi with id: %s not found - missing entity", id));
        }

        final User owner = entity.get().getOwner();
        if (owner == null) {
            // TODO security: hide core problem
            throw new ValidationException(String.format("Poi with id: %s not found - missing owner for entity", id));
        }

        final boolean userIsPoiOwner = owner.getId().equals(user.getId());
        if (!userIsPoiOwner) {
            // TODO security: hide core problem
            throw new ValidationException(String.format("Poi with id: %s not found - user is not an owner", id));
        }
        return entity.get();
    }

    default P updateSafe(final User user, final String id, final P entity) {
        entity.setId(findByIdSafe(user, id).getId());
        entity.setOwner(user);
        return update(entity);
    }
}
