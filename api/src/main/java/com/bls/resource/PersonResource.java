package com.bls.resource;

import com.bls.core.poi.Location;
import com.bls.core.poi.Person;
import com.bls.dao.PersonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Singleton
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDao<Person> personDao;
    private final String name;
    private final String surname;
    private final Location location;


    public PersonResource(PersonDao<Person> personDao, String name, String surname, Location location) {
        this.personDao = personDao;
        this.name = name;
        this.surname = surname;
        this.location = location;
    }

    @Path("/add")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person add(final Person person){ return personDao.add(person); }

    @Path("/remove")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person remove(final Person person){ return personDao.remove(person); }

    @Path("/update")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person update(final Person person){ return personDao.update(person); }
}
