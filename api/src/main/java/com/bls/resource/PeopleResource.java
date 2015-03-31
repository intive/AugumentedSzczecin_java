package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.person.Person;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private final CommonDao<Person> personDao;

    @Inject
    public PeopleResource(@Named("person") CommonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Person> getAll() {
        return personDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person add(final Person person) { return personDao.create(person); }
}
