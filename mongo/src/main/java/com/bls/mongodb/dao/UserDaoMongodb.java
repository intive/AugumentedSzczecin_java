package com.bls.mongodb.dao;

import com.bls.core.IdentifiableEntity;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.mongodb.core.MongodbMappableIdentifiableEntity;
import com.bls.mongodb.core.UserMongodb;
import com.google.inject.Inject;
import com.mongodb.DB;
import javassist.NotFoundException;
import org.mongojack.DBQuery;

/**
 * Created by Marcin Podlodowski on 3/14/15.
 */
public class UserDaoMongodb extends CommonDaoMongodb implements UserDao {
    
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
    
    @Override
    public User findByEmail(String email) throws NotFoundException {
/*
        User user =  (User) convert2coreModel((MongodbMappableIdentifiableEntity) 
                dbCollection.findOne(DBQuery.is("email", email)));
*/
        Object user = dbCollection.findOne(DBQuery.is("email", email));
        if (user == null) return null;
        
        return (User) convert2coreModel((MongodbMappableIdentifiableEntity) user);
    }

    @Override
    public void deleteByEmail(String email) {
        dbCollection.findAndRemove(DBQuery.is("email", email));
    }

    @Override
    public User add(User user) throws Exception {
        User existingUser = findByEmail(user.getEmail());
        if (existingUser != null) throw new Exception("Duplicate entry for [" + user.getEmail() + "]");
        create(user);
        return user;
    }

}
