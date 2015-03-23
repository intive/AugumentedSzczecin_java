package com.bls.dao;

<<<<<<< HEAD
import com.bls.core.IdentifiableEntity;
=======
import com.bls.core.Identifiable;
>>>>>>> master
import com.google.common.base.Optional;

/**
 * User specific DAO operation
 *
 * @param <E> Entity type
 * @param <K> Key type for entity E
 */
<<<<<<< HEAD
public interface UserDao<E extends IdentifiableEntity, K> extends CommonDao<E, K> {
=======
public interface UserDao<E extends Identifiable> extends CommonDao<E> {
>>>>>>> master

    /**
     * @param email for searching user
     * @return User found or Optional#empty() if not found
     */
    Optional<E> findByEmail(final String email);

    void deleteByEmail(final String email);
}
