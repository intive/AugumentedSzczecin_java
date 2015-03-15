package com.bls.mongodb.core;

import com.bls.core.IdentifiableEntity;

/**
 * Mongodb document mapped to some core entity
 *
 * @param <E> Core entity type
 */
public interface MongodbMappableEntity<E extends IdentifiableEntity<String>> {

    /**
     * @return core entity
     */
    E getCoreEntity();
}
