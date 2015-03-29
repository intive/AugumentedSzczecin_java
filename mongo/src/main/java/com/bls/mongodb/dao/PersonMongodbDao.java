package com.bls.mongodb.dao;

import com.bls.core.poi.Event;
import com.bls.core.poi.Person;
import com.bls.mongodb.core.EventMongodb;
import com.bls.mongodb.core.PersonMongodb;
import com.mongodb.DB;

import javax.inject.Inject;

/** Poi mongodb data provider */
public class PersonMongodbDao extends CommonMongodbDao<PersonMongodb, Person<String>, String> {

    @Inject
    public PersonMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<PersonMongodb> getMongodbModelType() {
        return PersonMongodb.class;
    }
}
