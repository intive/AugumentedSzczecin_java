package com.bls.core;

/**
 * Entity identified by key
 *
 * @param <K> some key type
 */

public abstract class IdentifiableEntity<K> implements Identifiable<K> {

    private K id; // TODO make it final


    public IdentifiableEntity(final K id) {
        this.id = id;
    }

    @Override
    public K getId() {
        return id;
    }

    // TODO remove me (make id final)
    @Override
    public void setId(final K id) {
        this.id = id;
    }
}