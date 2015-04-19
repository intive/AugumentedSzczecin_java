package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.geo.Location;
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

    Collection<E> findAll();

    Collection<E> findInRadius(final Location location, final Long radius);
}
