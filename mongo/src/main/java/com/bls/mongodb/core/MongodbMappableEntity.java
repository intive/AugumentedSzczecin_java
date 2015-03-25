package com.bls.mongodb.core;

import com.bls.core.Identifiable;

/**
 * Mongodb document mapped to some core entity
 *
 * @param <E> Core entity type
 */
public interface MongodbMappableEntity<E extends Identifiable<String>> {

    /**
     * @return core entity
     */
    E getCoreEntity();
}
