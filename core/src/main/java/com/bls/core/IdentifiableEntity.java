package com.bls.core;

/**
 * Entity identified by key
 *
 * @param <K> some key type
 */
public abstract class IdentifiableEntity<K> {

    private K id; // TODO make it final

    public IdentifiableEntity(final K id) {
        this.id = id;
    }

    public K getId() {
        return id;
    }

    // TODo remove me (make id final)
    public void setId(final K id) {
        this.id = id;
    }
}