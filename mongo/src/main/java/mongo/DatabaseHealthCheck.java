package mongo;

import com.google.inject.Inject;
import com.hubspot.dropwizard.guice.InjectableHealthCheck;
import com.mongodb.MongoClient;

public class DatabaseHealthCheck extends InjectableHealthCheck {

    private MongoClient mongoClient;

    @Inject
    public DatabaseHealthCheck(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public String getName() {
        return "MongoDB healthCheck";
    }

    @Override
    protected Result check() throws Exception {
        return mongoClient.getDatabaseNames().isEmpty() ? Result.unhealthy("No db's") : Result.healthy();
    }
}
