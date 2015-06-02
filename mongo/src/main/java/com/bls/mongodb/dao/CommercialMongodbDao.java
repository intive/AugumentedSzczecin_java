package com.bls.mongodb.dao;


import com.bls.core.commercial.Commercial;
import com.bls.dao.CommercialDao;
import com.bls.mongodb.core.CommercialMongodb;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;

import javax.inject.Inject;

public class CommercialMongodbDao extends CommonMongodbDao<CommercialMongodb, Commercial<String>, String> implements CommercialDao<Commercial<String>> {

    @Inject
    public CommercialMongodbDao(final DB db) {
        super(db);
        dbCollection.ensureIndex(new BasicDBObject("location", "2dsphere"));
    }

    @Override
    protected Class<CommercialMongodb> getMongodbModelType() {
        return CommercialMongodb.class;
    }

}
