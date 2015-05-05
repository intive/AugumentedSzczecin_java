package com.bls.mongodb.dao;

import com.bls.core.Identifiable;
import com.bls.core.geo.Location;
import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.bls.mongodb.core.MongodbMappableIdentifiableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Generic mongodb dao operations and mapping to/from core model
 *
 * @param <M> Entity type for mongodb mapping entity
 * @param <I> Entity type for core entity
 * @param <K> Key type for used inside core entity type
 */
public abstract class CommonMongodbDao<M extends MongodbMappableIdentifiableEntity, I extends Identifiable<K>, K> implements CommonDao<I> {

    private final static ObjectMapper MAPPER = MongoJackModule.configure(new ObjectMapper())
            .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
            .registerModule(new JodaModule());
    protected final JacksonDBCollection<M, String> dbCollection;

    public CommonMongodbDao(final DB db) {
        dbCollection = provideMongodbCollection(db, getMongodbCollectionName(), getMongodbModelType(), String.class);
    }

    private JacksonDBCollection<M, String> provideMongodbCollection(final DB db,
            final String mongoCollectionName,
            final Class<M> mongodbModelType,
            final Class<String> keyType) {
        return JacksonDBCollection.wrap(db.getCollection(mongoCollectionName), mongodbModelType, keyType, MAPPER);
    }

    /** @return Type of mondodb entity mapping */
    protected abstract Class<M> getMongodbModelType();

    /** @return mongodb collection name - default to mongo model class tyoe name */
    protected String getMongodbCollectionName() {
        return getMongodbModelType().getCanonicalName();
    }

    protected I convert2coreModel(final M mongodbEntity) {
        final I coreEntity = (I) mongodbEntity.getCoreEntity();
        coreEntity.setId(checkNotNull((K) mongodbEntity.id, "Missing mongodb generated id"));
        return coreEntity;
    }

    protected M convert2mongodbModel(I coreEntity) {
        final M mongodbEntity;
        try {
            mongodbEntity = getMongodbModelType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
        mongodbEntity.coreEntity = coreEntity;
        if (coreEntity.getId() != null) {
            mongodbEntity.id = String.valueOf(coreEntity.getId());
        }
        return mongodbEntity;
    }

    @Override
    public List<I> findAll() {
        final List<M> mongodbEntities = dbCollection.find().toArray();
        final List<I> coreEntities = Lists.newArrayListWithCapacity(mongodbEntities.size());
        coreEntities.addAll(mongodbEntities.stream().map(this::convert2coreModel).collect(Collectors.toList()));
        return coreEntities;
    }

    public List<I> find(final Location location, final Long radius, Collection<String> tags, User user){
        BasicDBList coordinates = new BasicDBList();
        coordinates.add(location.getLongitude());
        coordinates.add(location.getLatitude());

        BasicDBList geoParams = new BasicDBList();
        geoParams.add(coordinates);
        geoParams.add(metersToDegrees(radius));

        BasicDBObject query = new BasicDBObject("location",new BasicDBObject("$geoWithin",new BasicDBObject("$center", geoParams)));
        if(!tags.isEmpty()) {
            query.append("tags", new BasicDBObject("$in", tags));
        }
        if (user==null){
            query.append("owner", null);
        }

        final List<M> mongodbEntities = dbCollection.find(query).toArray();

        final List<I> coreEntities = Lists.newArrayListWithCapacity(mongodbEntities.size());
        coreEntities.addAll(mongodbEntities.stream().map(this::convert2coreModel).collect(Collectors.toList()));
        return coreEntities;
    }

    public Float metersToDegrees(Long radiusInMeters){
        return radiusInMeters.floatValue() / 111119.99965975954f;
    }

    @Override
    public Optional<I> findById(final String coreEntityId) {
        final M mongodbEntity = dbCollection.findOneById(coreEntityId);
        return mongodbEntity != null ? Optional.of(convert2coreModel(mongodbEntity)) : Optional.absent();
    }

    @Override
    public I create(final I coreEntity) {
        return convert2coreModel(dbCollection.insert(convert2mongodbModel(coreEntity)).getSavedObject());
    }

    @Override
    public I update(final I coreEntity) {
        return convert2coreModel(dbCollection.save(convert2mongodbModel(coreEntity)).getSavedObject());
    }

    @Override
    public void delete(final I coreEntity) {
        dbCollection.removeById(String.valueOf(checkNotNull(coreEntity.getId(), "Missing entity id")));
    }

    @Override
    public void deleteById(final String coreEntityId) {
        dbCollection.removeById(String.valueOf(checkNotNull(coreEntityId, "Missing id")));
    }

    @Override
    public void deleteAll() {
        dbCollection.remove(DBQuery.empty());
    }
}
