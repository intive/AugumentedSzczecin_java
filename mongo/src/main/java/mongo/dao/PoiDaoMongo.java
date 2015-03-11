package mongo.dao;


import com.augmented.core.Poi;
import com.augmented.core.Tag;
import com.augmented.dao.PoiDao;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mongo.core.PoiMongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.QueryResults;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class PoiDaoMongo extends BasicDAO<PoiMongo, ObjectId> implements PoiDao {

    @Inject
    public PoiDaoMongo(final Datastore datastore) {
        super(PoiMongo.class, datastore);
    }


    //TODO delete this method
    public void clearAll() {
        deleteByQuery(createQuery());
    }

    public List<Poi> findAllPoi() {
        return mapToPoi(find());
    }


    public String savePoi(final Poi poi) {
        return save((PoiMongo) poi).getId().toString();
    }

    public PoiMongo getbyId(final String id) {
        return get(new ObjectId(id));
    }

    public List<Poi> findByTag(final Tag tag) {
        return mapToPoi(find(createQuery().field(tag.getClass().getSimpleName().toLowerCase()).equal(tag)));
    }

    public List<Poi> findByFieldAndValue(final String field, final String value) {
        return mapToPoi(find(createQuery().field(field).equal(value)));
    }

    private List<Poi> mapToPoi(QueryResults<PoiMongo> queryResults) {
        return queryResults.asList().stream().collect(Collectors.toList());
    }

}
