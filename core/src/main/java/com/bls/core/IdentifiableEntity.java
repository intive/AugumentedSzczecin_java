package com.bls.core;

/**
 * Entity identified by key
 *
 * @param <K> some key type
 */
public abstract class IdentifiableEntity<K> {

    private final K id;

    public IdentifiableEntity(final K id) {
        this.id = id;
    }

    public K getId() {
        return id;
    }
}