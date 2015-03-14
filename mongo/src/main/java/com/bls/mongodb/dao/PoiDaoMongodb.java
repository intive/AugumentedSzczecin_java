package com.bls.mongodb.dao;

import javax.inject.Inject;

import com.bls.core.IdentifiableEntity;
import com.bls.core.poi.Poi;
import com.bls.mongodb.core.MongodbMappableIdentifiableEntity;
import com.bls.mongodb.core.PoiMongodb;
import com.mongodb.DB;

/** Poi mongodb data provider */
// TODO generify me
public class PoiDaoMongodb extends CommonDaoMongodb {

    @Inject
    public PoiDaoMongodb(final DB db) {
        super(db);
    }

    @Override
    protected Class<PoiMongodb> getMongodbModelType() {
        return PoiMongodb.class;
    }

    @Override
    protected IdentifiableEntity convert2coreModel(final MongodbMappableIdentifiableEntity mongodbEntity) {
        // TODO find generic way to map mongo model to core
        Poi<String> poi = (Poi<String>) mongodbEntity.getCoreEntity();
        return new Poi<>(mongodbEntity.getId(), poi.getName(), poi.getTag(), poi.getLocation());
    }

    @Override
    protected MongodbMappableIdentifiableEntity convert2mongodbModel(final IdentifiableEntity coreEntity) {
        final PoiMongodb poiMongodb = new PoiMongodb();
        poiMongodb.poi = (Poi<String>) coreEntity;
        poiMongodb.id = (String) coreEntity.getId();
        return poiMongodb;
    }
}
