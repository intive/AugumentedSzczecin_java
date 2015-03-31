package com.bls.mongodb;

import java.net.UnknownHostException;

import javax.inject.Named;

import com.bls.dao.CommonDao;
import com.bls.dao.PersonDao;
import com.bls.dao.UserDao;
import com.bls.mongodb.dao.EventMongodbDao;
import com.bls.mongodb.dao.PersonMongodbDao;
import com.bls.mongodb.dao.PoiMongodbDao;
import com.bls.mongodb.dao.UserMongodbDao;
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

    @Singleton
    @Provides
    public UserDao provideUserDao(final DB mongodb) {
        return new UserMongodbDao(mongodb);
    }

    @Singleton
    @Provides
    @Named("poi")
    public CommonDao providePoiDao(final DB mongodb) {
        return new PoiMongodbDao(mongodb);
    }

    @Singleton
    @Provides
    @Named("event")
    public CommonDao provideEventDao(final DB mongodb) {
        return new EventMongodbDao(mongodb);
    }

    @Singleton
    @Provides
    @Named("person")
    public PersonDao providePersonDao(final DB mongodb) {
        return new PersonMongodbDao(mongodb);
    }
}
