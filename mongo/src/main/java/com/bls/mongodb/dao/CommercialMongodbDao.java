package com.bls.mongodb.dao;


import com.bls.core.commercial.Commercial;
import com.bls.core.user.User;
import com.bls.dao.CommercialDao;
import com.bls.mongodb.core.CommercialMongodb;
import com.mongodb.DB;

import javax.inject.Inject;

public class CommercialMongodbDao extends CommonMongodbDao<CommercialMongodb, Commercial<String>, String> implements CommercialDao<Commercial<String>> {

    @Inject
    public CommercialMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<CommercialMongodb> getMongodbModelType() {
        return CommercialMongodb.class;
    }

    @Override
    public Commercial<String> createWithOwner(Commercial<String> entity, User user) {
        entity.setOwner(user);
        return super.create(entity);
    }
}
