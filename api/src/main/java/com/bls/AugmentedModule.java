package com.bls;

import javax.inject.Singleton;

import com.bls.client.opendata.OpenDataClientConfiguration;
import com.bls.mongodb.MongodbConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class AugmentedModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public MongodbConfiguration provideMongodbConfiguration(AugmentedConfiguration config) {
        return config.getMongoConfig();
    }

    @Singleton
    @Provides
    public OpenDataClientConfiguration provideOpenDataClientConfiguration(AugmentedConfiguration config) {
        return config.getOpenDataClientConfig();
    }
}
