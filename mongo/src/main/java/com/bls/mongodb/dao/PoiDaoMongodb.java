package com.bls.mongodb.dao;

import javax.inject.Inject;

import com.bls.core.poi.Poi;
import com.bls.mongodb.core.PoiMongodb;
import com.mongodb.DB;

/** Poi mongodb data provider */
public class PoiDaoMongodb extends CommonDaoMongodb<PoiMongodb, Poi<String>, String> {

    @Inject
    public PoiDaoMongodb(final DB db) {
        super(db);
    }

    @Override
    protected Class<PoiMongodb> getMongodbModelType() {
        return PoiMongodb.class;
    }

    @Override
    protected Poi<String> convert2coreModel(final PoiMongodb mongodbEntity) {
        // TODO find generic way to map mongo model to core
        Poi<String> poi = mongodbEntity.getCoreEntity();
        return new Poi<>(mongodbEntity.getId(), poi.getName(), poi.getTag(), poi.getLocation());
    }

    @Override
    protected PoiMongodb convert2mongodbModel(final Poi<String> coreEntity) {
        final PoiMongodb poiMongodb = new PoiMongodb();
        poiMongodb.poi = coreEntity;
        poiMongodb.id = coreEntity.getId();
        return poiMongodb;
    }
}
