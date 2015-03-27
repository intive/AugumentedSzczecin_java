package com.bls.resource;

import com.bls.core.poi.Location;
import com.bls.core.poi.Person;
import com.bls.dao.CommonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Singleton
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final CommonDao<Person> personDao;
    private final String name;
    private final String surname;
    private final Location location;


    public PersonResource(CommonDao<Person> personDao, String name, String surname, Location location) {
        this.personDao = personDao;
        this.name = name;
        this.surname = surname;
        this.location = location;
    }


    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person add(final Person person){ return personDao.create(person); }

    @Path("/delete")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void delete(final Person person){ personDao.delete(person); }

    @Path("/update")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person update(final Person person){ return personDao.update(person); }
}
