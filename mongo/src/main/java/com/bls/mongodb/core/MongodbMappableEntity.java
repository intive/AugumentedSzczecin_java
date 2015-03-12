package com.bls.mongodb.core;

import com.bls.core.IdentifiableEntity;

public interface MongodbMappableEntity<E extends IdentifiableEntity> {

    E getCoreEntity();
}
