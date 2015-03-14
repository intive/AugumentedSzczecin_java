package com.bls.mongodb.core;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** @param <E> Entity type to be mapped to mongodb */
public abstract class MongodbMappableIdentifiableEntity implements MongodbMappableEntity<IdentifiableEntity> {

    @Id
    @ObjectId
    @JsonProperty("_id")
    public String id;

    @JsonIgnore
    public String getId() {
        return id;
    }
}
