package com.bls.rdbms.dao;

import com.bls.core.geo.Location;
import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.bls.rdbms.core.PoiJpa;
import com.google.common.base.Optional;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.Collection;

/** Poi jpa data provider */
public class PoiJpaDao extends CommonJpaDao<PoiJpa, Poi<Long>> {

    @Inject
    public PoiJpaDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected PoiJpa convert2jpa(final Poi<Long> coreEntity) {
        final PoiJpa result = new PoiJpa();
        result.setId(coreEntity.getId());
        result.setName(coreEntity.getName());
        // TODO
//        result.setCategory(coreEntity.getCategory());
        result.setLongitude(coreEntity.getLocation().getLongitude());
        result.setLatitude(coreEntity.getLocation().getLatitude());
        return result;
    }

    @Override
    protected Poi<Long> convert2core(final PoiJpa jpaEntity) {
        // TODO
        //        return new Poi<>(jpaEntity.getId(), jpaEntity.getName(), jpaEntity.getTag(), new Location(jpaEntity.getLongitude(),
        // jpaEntity.getLatitude()));
        return null;
    }

    @Override
    public Collection<Poi<Long>> find(Location location, Long radius, Collection<String> tags, Optional<User> user){
        return null;
    }
}

