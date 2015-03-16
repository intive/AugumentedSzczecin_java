package com.bls;

import org.glassfish.hk2.utilities.Binder;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.mongodb.MongodbModule;
import com.bls.rdbms.RdbmsModule;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.hubspot.dropwizard.guice.GuiceBundle.Builder;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static com.google.common.base.Preconditions.checkNotNull;

public class AugmentedApplication extends Application<AugmentedConfiguration> {

    private Injector guiceInjector;

    public static void main(final String[] args) throws Exception {
        new AugmentedApplication().run(args);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void initialize(final Bootstrap<AugmentedConfiguration> bootstrap) {
        // setup java 8 support for dropwizard
        bootstrap.addBundle(new Java8Bundle());

        // setup guice builder
        final Builder<AugmentedConfiguration> configurationBuilder = GuiceBundle.<AugmentedConfiguration>newBuilder().addModule(
                new AugmentedModule()) //
                .enableAutoConfig(getClass().getPackage().getName()) //
                .setConfigClass(AugmentedConfiguration.class);

        // decide which db backend should we running
        final boolean isMongoEnabled = true;
        if (isMongoEnabled) {
            configurationBuilder.addModule(new MongodbModule());
        } else {
            configurationBuilder.addModule(new HibernateModule(bootstrap));
            configurationBuilder.addModule(new RdbmsModule());
        }

        // eagerly inject all dependencies: note Stage.DEVELOPMENT it's a hack
        final GuiceBundle<AugmentedConfiguration> guiceBundle = configurationBuilder.build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);

        // save for later authentication setup
        guiceInjector = guiceBundle.getInjector();
    }

    @Override
    public void run(final AugmentedConfiguration augmentedConfiguration, final Environment environment) throws Exception {
        // TODO think about implementing Injectable...AuthProvider and remove all tricks
        environment.jersey().register(provideBasicAuthenticator());
    }

    private Binder provideBasicAuthenticator() {
        final UserDao userDao = checkNotNull(guiceInjector, "Guice injector empty").getInstance(UserDao.class);
        return AuthFactory.binder(new BasicAuthFactory(new BasicAuthenticator(userDao), "Basic auth", User.class));
    }
}
