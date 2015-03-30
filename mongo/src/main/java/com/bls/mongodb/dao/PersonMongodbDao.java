package com.bls.mongodb.dao;

import javax.inject.Inject;

import com.bls.core.person.Person;
import com.bls.mongodb.core.PersonMongodb;
import com.mongodb.DB;

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
