package mongo;

import com.augmented.configuration.AugmentedConfiguration;
import com.augmented.core.PoiFactory;
import com.augmented.dao.PoiDao;
import com.codahale.metrics.health.HealthCheck;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import io.dropwizard.lifecycle.Managed;
import mongo.core.PoiMongoFactory;
import mongo.dao.PoiDaoMongo;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

@Singleton
public class DatabaseModule extends AbstractModule {

    private MongoClient mongoClient;

    @Override
    protected void configure() {
    }

    @Provides
    public MongoClient provideMongoClient(final AugmentedConfiguration config) throws UnknownHostException {
        if (mongoClient == null) {
            final String address = config.getMongoConfig().getHost() + ":" + config.getMongoConfig().getPort();
            mongoClient = new MongoClient(address);
        }
        return mongoClient;
    }

    @Provides
    public Datastore provideDatastore(final AugmentedConfiguration config, final MongoClient mongoClient) throws UnknownHostException {
        return new Morphia().createDatastore(mongoClient, config.getMongoConfig().getName());
    }

    @Provides
    @Named("databaseManager")
    public Managed provideDatabaseManager(final MongoClient mongoClient) {
        return new DatabaseManager(mongoClient);
    }

    @Provides
    @Named("databaseHealthCheck")
    public HealthCheck provideDatabaseHealthCheck(final MongoClient mongoClient) {
        return new DatabaseHealthCheck(mongoClient);
    }

    @Provides
    public PoiDao providePoiDao(final Datastore datastore) {
        return new PoiDaoMongo(datastore);
    }

    @Provides
    public PoiFactory providePoi() {
        return new PoiMongoFactory();
    }
}
