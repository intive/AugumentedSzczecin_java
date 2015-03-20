package com.bls.core;

/**
 * Identifiable contract
 *
 * @param <K> Type of id
 */
public interface Identifiable<K> {

    K getId();

    // TODO remove me (make id final)
    void setId(final K id);
}
