package mongo.core;


import com.augmented.core.Poi;
import com.augmented.core.PoiFactory;

public class PoiMongoFactory extends PoiFactory {

    @Override
    public Poi createNewInstance() {
        return new PoiMongo();
    }
}
