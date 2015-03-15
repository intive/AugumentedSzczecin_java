package com.bls;

import org.glassfish.hk2.utilities.Binder;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.mongodb.MongodbModule;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;

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
        bootstrap.addBundle(new Java8Bundle());

        final GuiceBundle<AugmentedConfiguration> guiceBundle = GuiceBundle.<AugmentedConfiguration>newBuilder().addModule(
                new AugmentedModule()) //
                .addModule(new MongodbModule()) //
                .enableAutoConfig(getClass().getPackage().getName()) //
                .setConfigClass(AugmentedConfiguration.class) //
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
        guiceInjector = guiceBundle.getInjector();
    }

    @Override
    public void run(final AugmentedConfiguration augmentedConfiguration, final Environment environment) throws Exception {
        // TODO think about implementing Injectable...AuthProvider and remove all tricks
        environment.jersey().register(provideBasicAuthenticator());
    }

    private Binder provideBasicAuthenticator() {
        final UserDao userDao = checkNotNull(guiceInjector, "Guice injector empty").getInstance(UserDao.class);
        return AuthFactory.binder(new BasicAuthFactory(new BasicAuthenticator(userDao), "Basic " + "auth", User.class));
    }
}
