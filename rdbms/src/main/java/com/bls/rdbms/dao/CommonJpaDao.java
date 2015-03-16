package com.bls.rdbms.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;

import com.bls.core.IdentifiableEntity;
import com.bls.dao.CommonDao;

import io.dropwizard.hibernate.AbstractDAO;

public abstract class CommonJpaDao<J, E extends IdentifiableEntity<K>, K> extends AbstractDAO<J> implements CommonDao<E, K> {

    public CommonJpaDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    protected abstract J convert2jpa(E coreEntity);
    protected abstract E convert2core(J jpaEntity);

    @Override
    public E create(final E entity) {
        return convert2core(persist(convert2jpa(entity)));
    }

    @Override
    public E update(final E entity) {
        return create(entity);
    }

    @Override
    public void delete(final E entity) {
        currentSession().delete(convert2jpa(entity));
    }

    @Override
    public void deleteById(final K id) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public E findById(final K id) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public Collection<E> findAll() {
        throw new IllegalStateException("Not implemented");
    }
}
