package com.bls;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import com.bls.mongodb.MongodbConfiguration;

public class AugmentedConfiguration extends Configuration {

    @JsonProperty("mongo")
    private MongodbConfiguration mongoConfig;

    public MongodbConfiguration getMongoConfig() {
        return mongoConfig;
    }
}
