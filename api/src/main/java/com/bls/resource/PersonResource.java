package com.bls.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bls.core.person.Person;
import com.bls.core.user.User;
import com.bls.dao.PersonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/people/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDao<Person> personDao;

    @Inject
    public PersonResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person get(@Auth final User user, @PathParam("id") final String id) {
        return personDao.findByIdSafe(user, id);
    }

    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person update(@Auth final User user, @PathParam("id") final String id, @Valid final Person person) {
        return personDao.updateSafe(user, id, person);
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth User user, @PathParam("id") final String id) {
        personDao.deleteById((String)personDao.findByIdSafe(user, id).getId());
    }

}
