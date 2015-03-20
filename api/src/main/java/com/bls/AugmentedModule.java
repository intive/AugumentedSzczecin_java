package com.bls;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.Binder;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.mongodb.MongodbConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;

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
