package com.bls.mongodb.dao;

import java.util.List;

import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import com.bls.core.IdentifiableEntity;
import com.bls.dao.CommonDao;
import com.bls.mongodb.core.MongodbMappableIdentifiableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.DB;

/**
 * Generic mongodb dao operations and mapping to/from core model
 *
 * @param <M> Entity type for mongodb mapping entity
 * @param <I> Entity type for core entity
 * @param <K> Key type for used inside core entity type
 */
public abstract class CommonDaoMongodb<M extends MongodbMappableIdentifiableEntity, I extends IdentifiableEntity,
        K> implements CommonDao<I, K> {

    private final static ObjectMapper MAPPER = MongoJackModule.configure(new ObjectMapper());
    private final JacksonDBCollection<M, String> dbCollection;

    public CommonDaoMongodb(final DB db) {
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
        return getMongodbModelType().getClass().getCanonicalName();
    }

    protected abstract I convert2coreModel(final M mongodbEntity);

    protected abstract M convert2mongodbModel(final I coreEntity);

    @Override
    public List<I> findAll() {
        final List<M> mongodbEntities = dbCollection.find().toArray();
        final List<I> coreEntities = Lists.newArrayListWithCapacity(mongodbEntities.size());
        for (final M mongodbEntity : mongodbEntities) {
            coreEntities.add(convert2coreModel(mongodbEntity));
        }
        return coreEntities;
    }

    @Override
    public I findbyId(final K coreEntityId) {
        return convert2coreModel(dbCollection.findOneById(String.valueOf(coreEntityId)));
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
        // TODO
    }

    @Override
    public void deleteById(final K coreEntityId) {
        // TODO
    }
}
