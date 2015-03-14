package com.bls.mongodb.dao;

import com.bls.core.IdentifiableEntity;
import com.bls.core.user.User;
import com.bls.mongodb.core.MongodbMappableIdentifiableEntity;
import com.bls.mongodb.core.UserMongodb;
import com.google.inject.Inject;
import com.mongodb.DB;

/**
 * Created by Marcin Podlodowski on 3/14/15.
 */
public class UserDaoMongodb extends CommonDaoMongodb {
    
    @Inject
    public UserDaoMongodb(final DB db) {
        super(db);
    }

    @Override
    protected Class getMongodbModelType() {
        return UserMongodb.class;
    }

    @Override
    protected IdentifiableEntity convert2coreModel(MongodbMappableIdentifiableEntity mongodbEntity) {
        User<String> user = (User<String>) mongodbEntity.getCoreEntity();
        return new User<>(mongodbEntity.getId(), user.getEmail(), user.getPassword());
    }

    @Override
    protected MongodbMappableIdentifiableEntity convert2mongodbModel(IdentifiableEntity coreEntity) {
        final UserMongodb userMongodb = new UserMongodb();
        userMongodb.user = (User<String>) coreEntity;
        userMongodb.id = (String) coreEntity.getId();
        return userMongodb;
    }

}
