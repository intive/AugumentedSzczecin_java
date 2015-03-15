package com.bls;

import com.bls.mongodb.MongodbConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class AugmentedConfiguration extends Configuration {

    public static final int PW_HASH_SECURITY_LEVEL = 10;
    private final MongodbConfiguration mongoConfig;

    @JsonCreator
    public AugmentedConfiguration(@JsonProperty(value = "mongo", required = false) final MongodbConfiguration mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    public MongodbConfiguration getMongoConfig() {
        return mongoConfig;
    }
}
