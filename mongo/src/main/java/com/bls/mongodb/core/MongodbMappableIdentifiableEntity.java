package com.bls.mongodb.core;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import com.bls.core.Identifiable;
import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/** @param <E> Entity type to be mapped to mongodb */
public abstract class MongodbMappableIdentifiableEntity<E extends Identifiable<String>> implements MongodbMappableEntity<E> {

    @JsonUnwrapped
    @JsonIgnoreProperties("id")
    public E coreEntity;

    @Id
    @ObjectId
    @JsonProperty("_id")
    public String id;

    @Override
    public E getCoreEntity() {
        return coreEntity;
    }
}
