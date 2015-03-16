package com.bls;

import com.bls.mongodb.MongodbConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AugmentedConfiguration extends Configuration {

    public static final int PW_HASH_SECURITY_LEVEL = 12;
    private final MongodbConfiguration mongoConfig;
    private final DataSourceFactory rdbmsConfig;

    @JsonCreator
    public AugmentedConfiguration(@JsonProperty(value = "mongodb", required = false) final MongodbConfiguration mongodbConfig,
            @JsonProperty(value = "rdbms", required = false) final DataSourceFactory rdbmsConfig) {
        this.mongoConfig = mongodbConfig;
        this.rdbmsConfig = rdbmsConfig;
    }

    public MongodbConfiguration getMongoConfig() {
        return mongoConfig;
    }

    public DataSourceFactory getRdbmsConfig() {
        return rdbmsConfig;
    }
}
