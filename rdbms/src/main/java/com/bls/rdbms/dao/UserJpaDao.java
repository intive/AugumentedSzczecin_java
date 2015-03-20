package com.bls.rdbms.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.rdbms.core.UserJpa;
import com.google.common.base.Optional;

/**
 * User dao for JPA entity. Some custom methods using criteria API.
 */
public class UserJpaDao extends CommonJpaDao<UserJpa, User<Long>> implements UserDao<User<Long>> {

    @Inject
    public UserJpaDao(final SessionFactory factory) {
        super(factory);
    }

    // TODO use object mapper for that job - it could be generic
    @Override
    protected UserJpa convert2jpa(final User<Long> coreEntity) {
        final UserJpa result = new UserJpa();
        result.setId(coreEntity.getId());
        result.setEmail(coreEntity.getEmail());
        result.setPassword(coreEntity.getPassword());
        return result;
    }

    // TODO use object mapper for that job - it could be generic
    @Override
    protected User<Long> convert2core(final UserJpa jpaEntity) {
        return new User<>(jpaEntity.getId(), jpaEntity.getEmail(), jpaEntity.getPassword());
    }

    @Override
    public Optional<User<Long>> findByEmail(final String email) {
        final UserJpa result = (UserJpa) currentCriteria().add(Restrictions.eq("email", email)).uniqueResult();
        return result == null ? Optional.absent() : Optional.of(convert2core(result));
    }

    @Override
    public void deleteByEmail(final String email) {
        throw new IllegalStateException("Not implemented");
    }
}

