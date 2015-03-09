package com.augmented.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class AugmentedConfiguration extends Configuration {

    @JsonProperty("mongo")
    private MongoConfig mongoConfig;

    public MongoConfig getMongoConfig() {
        return mongoConfig;
    }
}
