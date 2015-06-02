package com.bls.mongodb.dao;

import com.bls.core.place.Place;
import com.bls.core.user.User;
import com.bls.dao.PlaceDao;
import com.bls.mongodb.core.PlaceMongodb;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;

import javax.inject.Inject;

public class PlaceMongodbDao extends CommonMongodbDao<PlaceMongodb, Place<String>, String> implements PlaceDao<Place<String>> {

    @Inject
    public PlaceMongodbDao(final DB db) {
        super(db);
        dbCollection.ensureIndex(new BasicDBObject("location", "2dsphere"));
    }

    @Override
    protected Class<PlaceMongodb> getMongodbModelType() {
        return PlaceMongodb.class;
    }
}
