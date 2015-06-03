package com.bls.dao;

import java.util.Collection;

import com.bls.core.Identifiable;
import com.bls.core.geo.Location;
import com.bls.core.user.User;
import com.google.common.base.Optional;

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
            final Optional<User> user,
            final Optional<Integer> page,
            final Integer pageSize);
}
