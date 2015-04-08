package com.bls.mongodb;

import com.bls.dao.*;
import com.bls.mongodb.dao.*;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

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
    public CommonDao providePoiDao(final DB mongodb) {
        return new PoiMongodbDao(mongodb);
    }

    @Singleton
    @Provides
    public EventDao provideEventDao(final DB mongodb) {
        return new EventMongodbDao(mongodb);
    }

    @Singleton
    @Provides
    public PersonDao providePersonDao(final DB mongodb) {
        return new PersonMongodbDao(mongodb);
    }

    @Singleton
    @Provides
    public CommercialDao provideCommercialDao(final DB mongodb) { return new CommercialMongodbDao(mongodb); }
}
