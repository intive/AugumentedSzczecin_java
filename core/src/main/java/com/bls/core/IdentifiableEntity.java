package com.bls.core;

/**
 * Entity identified by key
 *
 * @param <K> some key type
 */
<<<<<<< HEAD
public abstract class IdentifiableEntity<K> {

    private final K id;
=======
public abstract class IdentifiableEntity<K> implements Identifiable<K> {

    private K id; // TODO make it final
>>>>>>> master

    public IdentifiableEntity(final K id) {
        this.id = id;
    }

<<<<<<< HEAD
    public K getId() {
        return id;
    }
=======
    @Override
    public K getId() {
        return id;
    }

    // TODO remove me (make id final)
    @Override
    public void setId(final K id) {
        this.id = id;
    }
>>>>>>> master
}