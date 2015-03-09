package mongo;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import io.dropwizard.lifecycle.Managed;

@Singleton
public class DatabaseManager implements Managed {

    private final MongoClient mongoClient;

    @Inject
    public DatabaseManager(final MongoClient mongoClient) {
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
