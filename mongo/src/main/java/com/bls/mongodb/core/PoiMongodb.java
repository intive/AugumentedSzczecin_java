package com.bls.mongodb.core;

import com.bls.core.IdentifiableEntity;
import com.bls.core.poi.Poi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

// TODO genrify me
public class PoiMongodb extends MongodbMappableIdentifiableEntity {

    @JsonUnwrapped
    @JsonIgnoreProperties("id")
    public Poi<String> poi;

    @Override
    public IdentifiableEntity getCoreEntity() {
        return poi;
    }
}
