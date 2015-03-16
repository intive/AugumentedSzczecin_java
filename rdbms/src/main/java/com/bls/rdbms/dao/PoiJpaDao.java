package com.bls.rdbms.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.bls.core.poi.Poi;
import com.bls.rdbms.core.PoiJpa;

/** Poi jpa data provider */
public class PoiJpaDao extends CommonJpaDao<PoiJpa, Poi<Long>, Long> {

    @Inject
    public PoiJpaDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected PoiJpa convert2jpa(final Poi<Long> coreEntity) {
        final PoiJpa result = new PoiJpa();
        result.setId(coreEntity.getId());
        result.setName(coreEntity.getName());
        return result;
    }

    @Override
    protected Poi<Long> convert2core(final PoiJpa jpaEntity) {
        // TODO
        return new Poi<>(jpaEntity.getId(), jpaEntity.getName(), null, null);
    }
}

