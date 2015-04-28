package com.bls;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.bls.client.opendata.OpenDataClientConfiguration;
import com.bls.mongodb.MongodbConfiguration;
import com.bls.resetpwd.ResetPasswordTokenConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.cache.CacheBuilder;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AugmentedConfiguration extends Configuration {

    public static final int PW_HASH_SECURITY_LEVEL = 12;
    public static final String RDBMS_ENTITIES_PACKAGE = "com.bls.rdbms.core";
    public static final String DBTYPE_PROPERTY_NAME = "DBTYPE";
    public static final String DEFAULT_AUTH_CACHE_SPEC = "maximumSize=1000,expireAfterAccess=1h";
    public static final int DEFAULT_TOKEN_TIME = 30;
    @Valid
    @NotNull
    private final DbType dbtype;
    @Valid
    @NotNull
    private final MongodbConfiguration mongoConfig;
    @Valid
    @NotNull
    private final DataSourceFactory rdbmsConfig;
    @Valid
    private final CacheBuilder<Object, Object> authCacheSpec;
    @Valid
    @NotNull
    private final OpenDataClientConfiguration openDataClientConfig;
    @Valid
    @NotNull
    private final ResetPasswordTokenConfiguration tokenConfig;

    @JsonCreator
    public AugmentedConfiguration(@JsonProperty(value = "dbtype") final DbType dbtype,
            @JsonProperty(value = "mongodb", required = false) final MongodbConfiguration mongodbConfig,
            @JsonProperty(value = "rdbms", required = false) final DataSourceFactory rdbmsConfig,
            @JsonProperty(value = "authCacheSpec", required = false) final String authCacheSpec,
            @JsonProperty(value = "openDataClient", required = true) final OpenDataClientConfiguration openDataClientConfig, 
            @JsonProperty(value = "resetPasswordToken", required = false) final ResetPasswordTokenConfiguration tokenConfig) {
        this.dbtype = dbtype;
        this.mongoConfig = mongodbConfig;
        this.rdbmsConfig = rdbmsConfig;
        this.openDataClientConfig = openDataClientConfig;
        this.authCacheSpec = CacheBuilder.from(authCacheSpec != null ? authCacheSpec : DEFAULT_AUTH_CACHE_SPEC);
        this.tokenConfig = tokenConfig != null ? tokenConfig : new ResetPasswordTokenConfiguration(DEFAULT_TOKEN_TIME);
    }

    public MongodbConfiguration getMongoConfig() {
        return mongoConfig;
    }

    public DataSourceFactory getRdbmsConfig() {
        return rdbmsConfig;
    }

    public DbType getDbtype() {
        return dbtype;
    }

    public CacheBuilder<Object, Object> getAuthCacheBuilder() {
        return authCacheSpec;
    }

    public OpenDataClientConfiguration getOpenDataClientConfig() {
        return openDataClientConfig;
    }

    public ResetPasswordTokenConfiguration getTokenConfig() {
        return tokenConfig;
    }

    enum DbType {
        RDBMS, MONGODB
    }
}
