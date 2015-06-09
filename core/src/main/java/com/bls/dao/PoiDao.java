package com.bls.dao;

import javax.validation.ValidationException;

import com.bls.core.place.Place;
import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.google.common.base.Optional;

import java.util.Collection;
import java.util.Collections;

public interface PoiDao<P extends Poi> extends CommonDao<P> {

    default P createWithOwner(final P entity, final User user) {
        entity.setOwner(user);
        return create(entity);
    }

    default P checkDuplicateAndCreate(final P entity, final User user) {
        final Long RADIUS = 10l;
        Optional<P> entityWithSameName = findNameDuplicateForOwner(user, entity.getName());

        if (entityWithSameName.isPresent()) {
            // Find 1 entity within {radius} meters around entity.location, owned by user
            Collection<P> entitiesWithSameNameWithinRange = find(
                    entity.getLocation(),
                    RADIUS,
                    Collections.<String>emptyList(),            // tags
                    Optional.of(entity.getName()),
                    Optional.absent(),                          // street
                    Collections.<Place.Subcategory>emptyList(), // subs
                    Optional.absent(),                          // paid
                    Optional.absent(),                          // open
                    Optional.of(user),                          // owner
                    Optional.of(0),                             // first page
                    1);                                         // 1 result

            if (!entitiesWithSameNameWithinRange.isEmpty()) {
                throw new ValidationException(String.format("Entity name duplicate within %d meters.", RADIUS));
            }
        }

        return createWithOwner(entity, user);
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

    default Optional<P> findNameDuplicateForOwner(final User user, final String name) {
        final Optional<P> entity = findOneByField("name", name);

        if (!entity.isPresent()) {
            return Optional.absent();
        }

        final User owner = entity.get().getOwner();
        final boolean userIsPoiOwner = owner.getId().equals(user.getId());
        if (userIsPoiOwner) {
            return entity;
        }

        return Optional.absent();
    }
}
