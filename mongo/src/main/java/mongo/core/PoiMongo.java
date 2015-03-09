package mongo.core;

import com.augmented.core.Poi;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class PoiMongo extends Poi {

    @Id
    private ObjectId id;

    @JsonIgnore
    public ObjectId getObjectId() {
        return id;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new ObjectId(id);
    }

}
