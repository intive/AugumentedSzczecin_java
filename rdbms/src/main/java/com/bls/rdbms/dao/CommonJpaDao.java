package com.bls.rdbms.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import com.bls.core.IdentifiableEntity;
import com.bls.dao.CommonDao;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.util.Generics;

public abstract class CommonJpaDao<J, E extends IdentifiableEntity> extends AbstractDAO<J> implements CommonDao<E> {

    private final Class<?> entityClass;

    public CommonJpaDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
        entityClass = Generics.getTypeParameter(getClass());
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
    public void deleteById(final String id) {
        final Object entity = currentSession().load(entityClass, Long.valueOf(id));
        currentSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public Optional<E> findById(final String id) {
        return Optional.fromNullable(convert2core((J) currentSession().get(entityClass, Long.valueOf(id))));
    }

    @Override
    public Collection<E> findAll() {
        final List<J> list = currentCriteria().list();
        final List<E> result = Lists.newArrayListWithCapacity(list.size());
        for (J entity : list) {
            result.add(convert2core(entity));
        }
        return result;
    }

    protected Criteria currentCriteria() {return currentSession().createCriteria(entityClass);}
}
