package com.bls.mongodb;

import com.google.inject.Inject;
import com.mongodb.MongoClient;

import io.dropwizard.lifecycle.Managed;

public class MongodbManager implements Managed {

    private final MongoClient mongoClient;

    @Inject
    public MongodbManager(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
