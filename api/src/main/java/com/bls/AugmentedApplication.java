package com.bls;

import com.bls.mongodb.MongodbModule;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AugmentedApplication extends Application<AugmentedConfiguration> {

    public static void main(String[] args) throws Exception {
        new AugmentedApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AugmentedConfiguration> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());

        final GuiceBundle<AugmentedConfiguration> guiceBundle = GuiceBundle.<AugmentedConfiguration>newBuilder()
                .addModule(new AugmentedModule()) //
                .addModule(new MongodbModule()) //
                .enableAutoConfig(getClass().getPackage().getName()) //
                .setConfigClass(AugmentedConfiguration.class) //
                .build(Stage.DEVELOPMENT);

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final AugmentedConfiguration augmentedConfiguration, final Environment environment) throws Exception {
    }
}
