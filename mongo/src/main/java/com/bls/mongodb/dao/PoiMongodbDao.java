package com.bls.mongodb.dao;

import javax.inject.Inject;

import com.bls.core.poi.Poi;
import com.bls.mongodb.core.PoiMongodb;
import com.mongodb.DB;

/** Poi mongodb data provider */
public class PoiMongodbDao extends CommonMongodbDao<PoiMongodb, Poi<String>, String> {

    @Inject
    public PoiMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<PoiMongodb> getMongodbModelType() {
        return PoiMongodb.class;
    }
}
