package com.bls.dao;

import java.util.Collection;

import com.bls.core.IdentifiableEntity;

/**
 * Common Dao for all entities
 *
 * @param <E> Entity type
 * @param <K> Entity key type
 */
public interface CommonDao<E extends IdentifiableEntity, K> {

    E create(final E entity);

    E update(final E entity);

    void delete(final E entity);

    void deleteById(final K id);

    void deleteAll();

    E findbyId(final K id);

    Collection<E> findAll();
}
