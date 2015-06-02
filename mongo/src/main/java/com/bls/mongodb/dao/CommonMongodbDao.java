package com.bls.mongodb.dao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.mongojack.Aggregation;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

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
import com.mongodb.BasicDBObject;
import com.mongodb.DB;

import static com.google.common.base.Preconditions.checkArgument;
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
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true).registerModule(new JodaModule());
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
    public List<I> findAll(final User owner) {
        final BasicDBObject owning = new BasicDBObject("owner.id", new BasicDBObject("$eq", owner.getId()));
        final List<M> mongodbEntities = dbCollection.find(owning).toArray();
        final List<I> coreEntities = Lists.newArrayListWithCapacity(mongodbEntities.size());
        coreEntities.addAll(mongodbEntities.stream().map(this::convert2coreModel).collect(Collectors.toList()));
        return coreEntities;
    }

    /**
     * Returns collection of POIs around {@param location}, within {@param radius} range.
     * Results may be optionally limited by tags if it's not empty.
     * Optional {@param user} indicates if the user is logged in or not.
     * Optional {@param page} enables pagination. Otherwise all matching POIs are returned.
     * Optional {@param pageSize} specifies number of results per page. (by default set in configuration file)
     *
     * @param location {@link Location}
     * @param radius   meters
     * @param tags     collection of tags, may be empty
     * @param user     optional {@link User} used to determine POI owner
     * @param page     optional - specifies a page to return
     * @param pageSize specifies page size
     * @return List of POIs matching criteria.
     */
    public List<I> find(final Location location,
            final Long radius,
            final Collection<String> tags,
            final Optional<User> user,
            final Optional<Integer> page,
            final Integer pageSize) {

        final BasicDBObject query = createQuery(location, radius, tags, user, page, pageSize);

        final Aggregation<M> queryWithPaging = addPagingAggregation(query, page, pageSize);

        final List<M> mongodbEntities = dbCollection.aggregate(queryWithPaging).results();

        final List<I> coreEntities = Lists.newArrayListWithCapacity(mongodbEntities.size());
        coreEntities.addAll(mongodbEntities.stream().map(this::convert2coreModel).collect(Collectors.toList()));

        return coreEntities;
    }

    private BasicDBObject createQuery(final Location location,
            final Long radius,
            final Collection<String> tags,
            final Optional<User> user,
            final Optional<Integer> page,
            final Integer pageSize) {

        BasicDBObject additionalQuery = new BasicDBObject();
        if (!tags.isEmpty()) {
            additionalQuery.append("tags", new BasicDBObject("$in", tags));
        }
        if (user.isPresent()) {
            additionalQuery.append("owner.id", new BasicDBObject("$eq", user.get().getId()));
        } else {
            additionalQuery.append("owner", new BasicDBObject("$eq", null));
        }

        BasicDBObject geoNearParams = new BasicDBObject();
        double[] near = {location.getLongitude(), location.getLatitude()};
        geoNearParams.append("near", near);
        geoNearParams.append("spherical", "true");
        geoNearParams.append("maxDistance", Math.toRadians(metersToDegrees(radius)));
        geoNearParams.append("distanceField", "dist");
        //        geoNearParams.append("distanceMultiplier", 6371009); // radius of the earth in meters
        geoNearParams.append("query", additionalQuery);
        if (page.isPresent()) {
            geoNearParams.append("limit", (page.get() + 1) * pageSize);
        }
        System.out.println("geoNearParams = " + geoNearParams);
        return new BasicDBObject("$geoNear", geoNearParams);
    }

    public Float metersToDegrees(Long radiusInMeters) {
        checkArgument(radiusInMeters != null, "Radius is required");
        return radiusInMeters.floatValue() / 111119.99965975954f;
    }

    private Aggregation<M> addPagingAggregation(final BasicDBObject query, final Optional<Integer> page, final int pageSize) {
        final Aggregation<M> aggregation;
        final int countToSkip = page.isPresent() ? page.get() * pageSize : 0;
        if (countToSkip > 0) {
            final BasicDBObject skip = new BasicDBObject("$skip", countToSkip);
            aggregation = new Aggregation<>(getMongodbModelType(), query, skip);
        } else {
            aggregation = new Aggregation<>(getMongodbModelType(), query);
        }
        return aggregation;
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
