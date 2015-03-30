package com.bls.resource;

import com.bls.core.poi.Person;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


@Singleton
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final CommonDao<Person> personDao;

    @Inject
    public PersonResource(@Named("person") CommonDao personDao) {
        this.personDao = personDao;
    }

    @Path("/people")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Person> getAll() {
        return personDao.findAll();
    }
    
    @Path("/person")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person add(final Person person){ return personDao.create(person); }

    @Path("/person/{id}")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person get(@PathParam("id") final String id) {
        return personDao.findById(id);
    }
    
    @Path("/person/{id}")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person update(@PathParam("id") final String id) {
        Person person = personDao.findById(id);
        return personDao.update(person);
    }

    @Path("/person/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@PathParam("id") final String id) { personDao.deleteById(id); }
}
