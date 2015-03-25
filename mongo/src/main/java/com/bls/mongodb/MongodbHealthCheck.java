package com.bls.mongodb;

import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.InjectableHealthCheck;
import com.mongodb.MongoClient;

public class MongodbHealthCheck extends InjectableHealthCheck {

    private MongoClient mongoClient;

    @Inject
    public MongodbHealthCheck(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

  /*  public MongodbHealthCheck() {
        this.mongoClient = mongoClient;
    } */

    @Override
    public String getName() {
        return "MongoDB healthCheck";
    }

    @Override
    protected Result check() throws Exception {
        return mongoClient.getDatabaseNames().isEmpty() ? Result.unhealthy("No db's") : Result.healthy();
    }
}
