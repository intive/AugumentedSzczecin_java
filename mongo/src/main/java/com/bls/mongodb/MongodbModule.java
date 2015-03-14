package com.bls.mongodb;

import java.net.UnknownHostException;

import javax.inject.Named;

import com.bls.dao.CommonDao;
import com.bls.mongodb.dao.PoiDaoMongodb;
import com.bls.mongodb.dao.UserDaoMongodb;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongodbModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public DB provideMongodb(final MongodbConfiguration config, final MongoClient mongoClient) throws UnknownHostException {
        return mongoClient.getDB(config.getDbname());
    }

    @Singleton
    @Provides
    public MongoClient provideMongodbClient(final MongodbConfiguration config) throws UnknownHostException {
        return config.buildMongoClient();
    }

    // TODO configure all DAOs at once -
    @Singleton
    @Provides
    @Named("user")
    public CommonDao provideUserDao(final DB mongodb) {
        return new UserDaoMongodb(mongodb);
    }

    @Singleton
    @Provides
    @Named("poi")
    public CommonDao providePoiDao(final DB mongodb) {
        return new PoiDaoMongodb(mongodb);
    }

}
