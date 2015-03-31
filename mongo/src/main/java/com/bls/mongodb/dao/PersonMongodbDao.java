package com.bls.mongodb.dao;

import javax.inject.Inject;

import com.bls.core.person.Person;
import com.bls.core.user.User;
import com.bls.dao.PersonDao;
import com.bls.mongodb.core.PersonMongodb;
import com.google.common.collect.Lists;
import com.mongodb.DB;
import org.mongojack.DBQuery;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** Poi mongodb data provider */
public class PersonMongodbDao extends CommonMongodbDao<PersonMongodb, Person<String>, String>
        implements PersonDao<Person<String>> {

    @Inject
    public PersonMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<PersonMongodb> getMongodbModelType() {
        return PersonMongodb.class;
    }

    @Override
    public List<Person<String>> findByUserId(String ownerid) {
        final List<PersonMongodb> mongodbEntities = dbCollection.find(DBQuery.is("ownerid", ownerid)).toArray();
        final List<Person<String>> coreEntities = Lists.newArrayListWithCapacity(mongodbEntities.size());
        coreEntities.addAll(mongodbEntities.stream().map(this::convert2coreModel).collect(Collectors.toList()));
        return coreEntities;
    }

}