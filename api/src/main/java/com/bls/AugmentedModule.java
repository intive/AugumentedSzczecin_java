package com.bls;

import com.bls.client.opendata.OpenDataClientConfiguration;
import com.bls.core.PagingConfiguration;
import com.bls.mongodb.MongodbConfiguration;
import com.bls.core.resetpwd.ResetPasswordTokenConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Singleton;

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
    
    @Singleton
    @Provides
    public ResetPasswordTokenConfiguration provideResetPasswordTokenConfiguration(AugmentedConfiguration config) {
        return config.getTokenConfig();
    }

    @Singleton
    @Provides
    public PagingConfiguration providePagingConfiguration(AugmentedConfiguration config) {
        return config.getPagingConfig();
    }
}
