package com.bls;

import javax.inject.Singleton;

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
}
