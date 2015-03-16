package com.bls.core;

public interface Identifiable<K> {

    K getId();

    // TODO remove me (make id final)
    void setId(final K id);
}
