package com.bls.mongodb.core;

<<<<<<< HEAD
import com.bls.core.IdentifiableEntity;

public interface MongodbMappableEntity<E extends IdentifiableEntity> {

=======
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
>>>>>>> master
    E getCoreEntity();
}
