package com.bls.mongodb.dao;

import org.mongojack.DBQuery;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.mongodb.core.UserMongodb;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mongodb.DB;

import static jersey.repackaged.com.google.common.base.Preconditions.checkState;

public class UserDaoMongodb extends CommonDaoMongodb<UserMongodb, User<String>, String> implements UserDao<User<String>, String> {

    @Inject
    public UserDaoMongodb(final DB db) {
        super(db);
    }

    @Override
    protected Class<UserMongodb> getMongodbModelType() {
        return UserMongodb.class;
    }

    @Override
    public Optional<User<String>> findByEmail(String email) {
        final UserMongodb user = dbCollection.findOne(DBQuery.is("email", email));
        if (user == null) {
            return Optional.absent();
        }
        return Optional.of(convert2coreModel(user));
    }

    @Override
    public void deleteByEmail(String email) {
        dbCollection.findAndRemove(DBQuery.is("email", email));
    }

    @Override
    public User<String> create(final User<String> user) {
        return super.create(checkDuplicate(user));
    }

    private User<String> checkDuplicate(final User<String> user) {
        Optional<User<String>> existingUser = findByEmail(user.getEmail());
        checkState(!existingUser.isPresent(), "User already in db: %s", user.getEmail());
        return user;
    }
}
