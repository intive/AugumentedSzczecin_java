package com.bls.rdbms;

import javax.inject.Named;

import org.hibernate.SessionFactory;

import com.bls.dao.CommonDao;
import com.bls.dao.UserDao;
import com.bls.rdbms.dao.PoiJpaDao;
import com.bls.rdbms.dao.UserJpaDao;
import com.google.inject.*;

/**
 * Providers for JPA DAO
 */
public class RdbmsModule implements Module {

    @Override
    public void configure(final Binder binder) {
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
