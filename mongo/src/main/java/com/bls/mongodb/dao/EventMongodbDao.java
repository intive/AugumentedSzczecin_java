package com.bls.mongodb.dao;

import javax.inject.Inject;

import com.bls.core.event.Event;
import com.bls.mongodb.core.EventMongodb;
import com.mongodb.DB;

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
