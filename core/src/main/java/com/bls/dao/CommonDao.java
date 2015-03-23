package com.bls.dao;

import java.util.Collection;
import com.bls.core.Identifiable;

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

    E findById(final String id);

    Collection<E> findAll();
}
