package com.bls;

import org.hibernate.SessionFactory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;

public class HibernateModule implements Module {

    public static final String JPA_ENTITY_PACKAGES = "com.bls.rdbms.core";

    @Override
    public void configure(final Binder binder) {
    }

    private final ScanningHibernateBundle<AugmentedConfiguration> hibernateBundle = new ScanningHibernateBundle<AugmentedConfiguration>(
            JPA_ENTITY_PACKAGES) {
        @Override
        public DataSourceFactory getDataSourceFactory(final AugmentedConfiguration configuration) {
            return configuration.getRdbmsConfig();
        }
    };
    private final MigrationsBundle<AugmentedConfiguration> migrationBundle = new MigrationsBundle<AugmentedConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(final AugmentedConfiguration configuration) {
            return configuration.getRdbmsConfig();
        }
    };

    public HibernateModule(Bootstrap<AugmentedConfiguration> bootstrap) {
        bootstrap.addBundle(migrationBundle);
        bootstrap.addBundle(hibernateBundle);
    }

    @Provides
    public SessionFactory provideSessionFactory() {
        return hibernateBundle.getSessionFactory();
    }
}