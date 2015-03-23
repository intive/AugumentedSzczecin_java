package com.bls;

<<<<<<< HEAD
=======
import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
>>>>>>> master
import org.glassfish.hk2.utilities.Binder;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.mongodb.MongodbModule;
<<<<<<< HEAD
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
=======
import com.bls.rdbms.RdbmsModule;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.hubspot.dropwizard.guice.GuiceBundle.Builder;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static com.bls.AugmentedConfiguration.DBTYPE_MONGODB;
import static com.bls.AugmentedConfiguration.DBTYPE_PROPERTY_NAME;
import static com.bls.AugmentedConfiguration.RDBMS_ENTITIES_PACKAGE;

public class AugmentedApplication extends Application<AugmentedConfiguration> {

    private Injector injector;
>>>>>>> master

    public static void main(final String[] args) throws Exception {
        new AugmentedApplication().run(args);
    }

<<<<<<< HEAD
=======
    private static boolean isDbTypeSetToMongodb(final String dbTypePropertyValue) {
        return dbTypePropertyValue == null || dbTypePropertyValue.equals(DBTYPE_MONGODB);
    }

>>>>>>> master
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void initialize(final Bootstrap<AugmentedConfiguration> bootstrap) {
<<<<<<< HEAD
        bootstrap.addBundle(new Java8Bundle());

        final GuiceBundle<AugmentedConfiguration> guiceBundle = GuiceBundle.<AugmentedConfiguration>newBuilder().addModule(
                new AugmentedModule()) //
                .addModule(new MongodbModule()) //
                .enableAutoConfig(getClass().getPackage().getName()) //
                .setConfigClass(AugmentedConfiguration.class) //
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);
        guiceInjector = guiceBundle.getInjector();
=======
        // setup java 8 support for dropwizard
        bootstrap.addBundle(new Java8Bundle());

        // Enable configuration variable substitution with system property values
        final StrSubstitutor systemPropertyStrSubstitutor = new StrSubstitutor(StrLookup.systemPropertiesLookup());
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), systemPropertyStrSubstitutor));

        // setup guice builder
        final Builder<AugmentedConfiguration> guiceConfiguration = GuiceBundle.<AugmentedConfiguration>newBuilder().addModule(
                new AugmentedModule()) //
                .enableAutoConfig(getClass().getPackage().getName()) //
                .setConfigClass(AugmentedConfiguration.class);

        // setup db backend based - choice i based on system property value
        if (isDbTypeSetToMongodb(systemPropertyStrSubstitutor.getVariableResolver().lookup(DBTYPE_PROPERTY_NAME))) {
            guiceConfiguration.addModule(new MongodbModule());
        } else {
            bootstrap.addBundle(createMigrationBundle());
            guiceConfiguration.addModule(new RdbmsModule<AugmentedConfiguration>(bootstrap) {
                @Override
                public DataSourceFactory getRealDataSourceFactory(final AugmentedConfiguration configuration) {
                    return configuration.getRdbmsConfig();
                }

                @Override
                public String getPackagesToScanForEntities() {
                    return RDBMS_ENTITIES_PACKAGE;
                }
            });
        }

        // eagerly inject all dependencies: note Stage.DEVELOPMENT it's a hack
        final GuiceBundle<AugmentedConfiguration> guiceBundle = guiceConfiguration.build(Stage.DEVELOPMENT);
        bootstrap.addBundle(guiceBundle);

        injector = guiceBundle.getInjector();
    }

    private MigrationsBundle<AugmentedConfiguration> createMigrationBundle() {
        return new MigrationsBundle<AugmentedConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(final AugmentedConfiguration configuration) {
                return configuration.getRdbmsConfig();
            }
        };
>>>>>>> master
    }

    @Override
    public void run(final AugmentedConfiguration augmentedConfiguration, final Environment environment) throws Exception {
<<<<<<< HEAD
        // TODO think about implementing Injectable...AuthProvider and remove all tricks
        environment.jersey().register(provideBasicAuthenticator());
    }

    private Binder provideBasicAuthenticator() {
        final UserDao<User<String>, String> userDao = checkNotNull(guiceInjector, "Guice injector empty").getInstance(UserDao.class);
        return AuthFactory.binder(new BasicAuthFactory(new BasicAuthenticator(userDao), "Basic " + "auth", User.class));
=======
        registerAuthorizationProviders(augmentedConfiguration, environment);
    }

    private void registerAuthorizationProviders(final AugmentedConfiguration augmentedConfiguration, final Environment environment) {
        final UserDao userDao = injector.getInstance(UserDao.class);
        final BasicAuthenticator basicAuthenticator = new BasicAuthenticator(userDao);
        final CachingAuthenticator cachingAuthenticator = new CachingAuthenticator(environment.metrics(), basicAuthenticator,
                augmentedConfiguration.getAuthCacheBuilder());
        final Binder authBinder = AuthFactory.binder(new BasicAuthFactory(cachingAuthenticator, "Basic auth", User.class));
        environment.jersey().register(authBinder);
>>>>>>> master
    }
}
