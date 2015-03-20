package com.bls.rdbms;

import javax.inject.Named;

import org.hibernate.SessionFactory;

import com.bls.dao.CommonDao;
import com.bls.dao.UserDao;
import com.bls.rdbms.dao.PoiJpaDao;
import com.bls.rdbms.dao.UserJpaDao;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.ScanningHibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Providers for JPA DAO that uses Hibernate ORM with liquibase library for RDBMS schema migrations
 */
public abstract class RdbmsModule<T extends Configuration> implements Module {

    private final HibernateBundle<T> hibernateBundle = new ScanningHibernateBundle<T>(getPackagesToScanForEntities()) {
        @Override
        public DataSourceFactory getDataSourceFactory(final T configuration) {
            return getRealDataSourceFactory(configuration);
        }
    };

    public RdbmsModule(Bootstrap<T> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void configure(final Binder binder) {
    }

    public abstract DataSourceFactory getRealDataSourceFactory(final T configuration);

    public abstract String getPackagesToScanForEntities();

    @Provides
    public SessionFactory provideSessionFactory() {
        return checkNotNull(hibernateBundle, "Hibernate bundle is null").getSessionFactory();
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(final SessionFactory sessionFactory) {
        return new UserJpaDao(sessionFactory);
    }

    @Singleton
    @Provides
    @Named("poi")
    public CommonDao providePoiDao(final SessionFactory sessionFactory) {
        return new PoiJpaDao(sessionFactory);
    }
}
