package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.person.Person;
import com.bls.core.user.User;
import com.bls.dao.PersonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private final PersonDao<Person> personDao;

    @Inject
    public PeopleResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Person> getAll(@Auth User user) {
        return personDao.findAll(user);
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person add(@Auth User user, @Valid final Person person) { return personDao.createWithOwner(person, user); }
}
