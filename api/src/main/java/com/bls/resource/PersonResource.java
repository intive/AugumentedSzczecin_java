package com.bls.resource;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
public class PersonResource {

    private final CommonDao<Person> personDao;

    @Inject
    public PersonResource(@Named("person") CommonDao personDao) {
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

    @Path("/{id}")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person get(@PathParam("id") final String id) {
        return personDao.findById(id);
    }

    @Path("/{id}")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person update(@PathParam("id") final String id) {
        Person person = personDao.findById(id);
        return personDao.update(person);
    }

    @Path("/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@PathParam("id") final String id) { personDao.deleteById(id); }
}
