package com.bls;

import javax.inject.Singleton;

<<<<<<< HEAD
=======
import org.glassfish.hk2.utilities.Binder;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
>>>>>>> master
import com.bls.mongodb.MongodbConfiguration;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

<<<<<<< HEAD
=======
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;

>>>>>>> master
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
