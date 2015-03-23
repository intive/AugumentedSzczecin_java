package com.bls.dao;

import java.util.Collection;

<<<<<<< HEAD
import com.bls.core.IdentifiableEntity;

/**
 * Common behaviour for all DAO
 *
 * @param <E> Entity type
 * @param <K> Entity key type
 */
public interface CommonDao<E extends IdentifiableEntity, K> {
=======
import com.bls.core.Identifiable;

/**
 * Common behaviour for all DAO. Entity key was set to String.
 *
 * @param <E> Entity type
 */
public interface CommonDao<E extends Identifiable> {
>>>>>>> master

    E create(final E entity);

    E update(final E entity);

    void delete(final E entity);

<<<<<<< HEAD
    void deleteById(final K id);

    void deleteAll();

    E findbyId(final K id);
=======
    void deleteById(final String id);

    void deleteAll();

    E findById(final String id);
>>>>>>> master

    Collection<E> findAll();
}
