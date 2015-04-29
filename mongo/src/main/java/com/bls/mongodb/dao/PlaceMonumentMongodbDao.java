package com.bls.mongodb.dao;

import com.bls.core.place_monument.PlaceMonument;
import com.bls.dao.PlaceMonumentDao;
import com.bls.mongodb.core.PlaceMonumentMongodb;
import com.mongodb.DB;

import javax.inject.Inject;

public class PlaceMonumentMongodbDao extends CommonMongodbDao<PlaceMonumentMongodb, PlaceMonument<String>, String> implements PlaceMonumentDao<PlaceMonument<String>> {

    @Inject
    public PlaceMonumentMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<PlaceMonumentMongodb> getMongodbModelType() {
        return PlaceMonumentMongodb.class;
    }

}