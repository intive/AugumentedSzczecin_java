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
import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.bls.dao.PersonDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import io.dropwizard.auth.Auth;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.servlets.assets.ResourceNotFoundException;

@Singleton
@Path("/user/{userid}/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDao personDao;

    @Inject
    public PersonResource(@Named("person") PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<Person> getByUserId(@Auth final User user, 
                                          @PathParam("userid") final String pathid) throws AuthenticationException {
        if (user.getId().equals(pathid)) 
            return personDao.findByUserId((String) user.getId());
        
        throw new AuthenticationException("Access denied.");
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person add(@Auth final User user, @PathParam("userid") final String pathid, final Person person) {
        person.setOwnerid((String) user.getId());
        return (Person) personDao.create(person);
    }

    @Path("/{id}")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person get(@Auth final User user, @PathParam("userid") final String pathid, @PathParam("id") final String id) 
            throws AuthenticationException {
        if (user.getId().equals(pathid))
            return (Person) personDao.findById(id);
        
        throw new AuthenticationException("Access denied.");
    }

    @Path("/{id}")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Person update(@Auth final User user, @PathParam("userid") final String pathid, 
                         @PathParam("id") final String id) throws AuthenticationException {
        Person person = null;
        if (user.getId().equals(pathid))
            person = (Person) personDao.findById(id);
        else 
            throw new AuthenticationException("Access denied.");
        
        if (person == null) throw new ResourceNotFoundException(new Throwable("Resource not found."));
        return (Person) personDao.update(person);
    }

    @Path("/{id}")
    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void deleteById(@Auth final User user, @PathParam("userid") final String pathid,
                           @PathParam("id") final String id) throws AuthenticationException {
        Person person = null;
        if (user.getId().equals(pathid))
            person = (Person) personDao.findById(id);
        else
            throw new AuthenticationException("Access denied.");

        if (person == null) throw new ResourceNotFoundException(new Throwable("Resource not found."));

        personDao.deleteById(id);
    }
    
}
