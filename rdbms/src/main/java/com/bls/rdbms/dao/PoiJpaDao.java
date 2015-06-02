package com.bls.rdbms.dao;

import java.util.Collection;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.bls.core.geo.Location;
import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.bls.rdbms.core.PoiJpa;
import com.google.common.base.Optional;

/** Poi jpa data provider */
public class PoiJpaDao extends CommonJpaDao<PoiJpa, Poi<Long>> {

    @Inject
    public PoiJpaDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected PoiJpa convert2jpa(final Poi<Long> coreEntity) {
        throw new IllegalStateException("Unimplemented");
    }

    @Override
    protected Poi<Long> convert2core(final PoiJpa jpaEntity) {
        throw new IllegalStateException("Unimplemented");
    }

    @Override
    public Collection<Poi<Long>> find(Location location,
            Long radius,
            Collection<String> tags,
            Optional<User> user,
            Optional<Integer> page,
            Integer pageSize) {
        throw new IllegalStateException("Unimplemented");
    }
}

