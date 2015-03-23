package com.bls.mongodb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import com.bls.core.Identifiable;
import com.bls.dao.CommonDao;
import com.bls.mongodb.core.MongodbMappableIdentifiableEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mongodb.DB;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Generic mongodb dao operations and mapping to/from core model
 *
 * @param <M> Entity type for mongodb mapping entity
 * @param <I> Entity type for core entity
 * @param <K> Key type for used inside core entity type
 */
public abstract class CommonMongodbDao<M extends MongodbMappableIdentifiableEntity, I extends Identifiable<K>, K> implements CommonDao<I> {

    private final static ObjectMapper MAPPER = MongoJackModule.configure(new ObjectMapper());
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
        return getMongodbModelType().getClass().getCanonicalName();
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

    @Override
    public I findById(final String coreEntityId) {
        return convert2coreModel(dbCollection.findOneById(coreEntityId));
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
