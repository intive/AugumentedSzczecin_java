package com.bls.rdbms.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.rdbms.core.UserJpa;
import com.google.common.base.Optional;

/**
 * Sample user dao for JPA DAO
 */
public class UserJpaDao extends CommonJpaDao<UserJpa, User<Long>, Long> implements UserDao<User<Long>, Long> {

    @Inject
    public UserJpaDao(final SessionFactory factory) {
        super(factory);
    }

    @Override
    protected UserJpa convert2jpa(final User<Long> coreEntity) {
        final UserJpa result = new UserJpa();
        result.setId(coreEntity.getId());
        result.setEmail(coreEntity.getEmail());
        result.setPassword(coreEntity.getPassword());
        return result;
    }

    @Override
    protected User<Long> convert2core(final UserJpa jpaEntity) {
        return new User<>(jpaEntity.getId(), jpaEntity.getEmail(), jpaEntity.getPassword());
    }

    @Override
    public Optional<User<Long>> findByEmail(final String email) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public void deleteByEmail(final String email) {
        throw new IllegalStateException("Not implemented");
    }
}

