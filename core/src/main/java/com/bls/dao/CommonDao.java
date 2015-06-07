package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.geo.Location;
import com.bls.core.place.Place;
import com.bls.core.user.User;
import com.google.common.base.Optional;

import java.util.Collection;

/**
 * Common behaviour for all DAO. Entity key was set to String.
 *
 * @param <E> Entity type
 */
public interface CommonDao<E extends Identifiable> {

    E create(final E entity);
    
    E update(final E entity);

    void delete(final E entity);

    void deleteById(final String id);

    void deleteAll();

    Optional<E> findById(final String id);

    Collection<E> findAll(final User owner);

    Collection<E> find(final Location location,
            final Long radius,
            Collection<String> tags,
            final Optional<String> name,
            final Optional<String> street,
            Collection<Place.Subcategory> subcat,
            final Optional<Boolean> paid,
            final Optional<Boolean> open,
            final Optional<User> user,
            final Optional<Integer> page,
            final Integer pageSize);
}
