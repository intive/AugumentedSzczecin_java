package com.bls.dao;

import com.bls.core.Identifiable;
import com.google.common.base.Optional;

/**
 * User specific DAO operation
 *
 * @param <E> Entity type
 * @param <K> Key type for entity E
 */
public interface UserDao<E extends Identifiable> extends CommonDao<E> {

    /**
     * @param email for searching user
     * @return User found or Optional#empty() if not found
     */
    Optional<E> findByEmail(final String email);

    void deleteByEmail(final String email);
}
