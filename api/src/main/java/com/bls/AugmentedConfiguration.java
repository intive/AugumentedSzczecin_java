package com.bls;

import com.bls.mongodb.MongodbConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilder;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AugmentedConfiguration extends Configuration {

    public static final int PW_HASH_SECURITY_LEVEL = 12;
    public static final String RDBMS_ENTITIES_PACKAGE = "com.bls.rdbms.core";
    public static final String DBTYPE_PROPERTY_NAME = "DBTYPE";
    public static final String DBTYPE_MONGODB = "mongodb";
    public static final String DEFAULT_AUTH_CACHE_SPEC = "maximumSize=1000,expireAfterAccess=1h";
    private final String dbtype;
    private final MongodbConfiguration mongoConfig;
    private final DataSourceFactory rdbmsConfig;
    private final CacheBuilder<Object, Object> authCacheSpec;

    @JsonCreator
    public AugmentedConfiguration(@JsonProperty(value = "dbtype") final String dbtype,
            @JsonProperty(value = "mongodb", required = false) final MongodbConfiguration mongodbConfig,
            @JsonProperty(value = "rdbms", required = false) final DataSourceFactory rdbmsConfig,
            @JsonProperty(value = "authCacheSpec", required = false) final String authCacheSpec) {
        this.dbtype = dbtype;
        this.mongoConfig = mongodbConfig;
        this.rdbmsConfig = rdbmsConfig;
        this.authCacheSpec = CacheBuilder.from(authCacheSpec != null ? authCacheSpec : DEFAULT_AUTH_CACHE_SPEC);
    }

    public MongodbConfiguration getMongoConfig() {
        return mongoConfig;
    }

    public DataSourceFactory getRdbmsConfig() {
        return rdbmsConfig;
    }

    public String getDbtype() {
        return dbtype;
    }

    public CacheBuilder<Object, Object> getAuthCacheBuilder() {
        return authCacheSpec;
    }
}
