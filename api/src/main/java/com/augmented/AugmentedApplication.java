package com.augmented;

import com.augmented.configuration.AugmentedConfiguration;
import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import mongo.DatabaseModule;

public class AugmentedApplication extends Application<AugmentedConfiguration> {

    public static void main(String[] args) throws Exception {
        new AugmentedApplication().run(args);
    }

    private Injector injector;

    @Override
    public void initialize(Bootstrap<AugmentedConfiguration> bootstrap) {
        final GuiceBundle<AugmentedConfiguration> guiceBundle = GuiceBundle.<AugmentedConfiguration>newBuilder()
                .addModule(new DatabaseModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(AugmentedConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);
        injector = guiceBundle.getInjector();


    }

    @Override
    public void run(AugmentedConfiguration augmentedConfiguration, Environment environment) throws Exception {
        environment.lifecycle().manage(createDatabaseManager());
        environment.healthChecks().register("mongo", createDatabaseHealthCheck());
    }

    private Managed createDatabaseManager() {
        return injector.getInstance(Key.get(Managed.class, Names.named("databaseManager")));
    }

    private HealthCheck createDatabaseHealthCheck() {
        return injector.getInstance(Key.get(HealthCheck.class, Names.named("databaseHealthCheck")));
    }
}
