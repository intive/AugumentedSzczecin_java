package com.bls.mongodb.core;

import com.bls.core.poi.Poi;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class PoiMongodb extends MongodbMappableIdentifiableEntity<Poi<String>> {

    @JsonUnwrapped
    @JsonIgnoreProperties("id")
    public Poi<String> poi;

    @Override
    public Poi<String> getCoreEntity() {
        return poi;
    }
}
=======

public class PoiMongodb extends MongodbMappableIdentifiableEntity<Poi<String>> {}
>>>>>>> master
