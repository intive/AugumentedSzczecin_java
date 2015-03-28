package com.bls.mongodb.dao;

import com.bls.core.poi.Event;
import com.bls.core.poi.Poi;
import com.bls.mongodb.core.EventMongodb;
import com.bls.mongodb.core.PoiMongodb;
import com.mongodb.DB;

import javax.inject.Inject;

/** Poi mongodb data provider */
public class EventMongodbDao extends CommonMongodbDao<EventMongodb, Event<String>, String> {

    @Inject
    public EventMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<EventMongodb> getMongodbModelType() {
        return EventMongodb.class;
    }
}
