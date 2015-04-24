package com.bls.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.dao.ResetPasswordTokenDao;
import com.bls.core.user.ResetPasswordToken;
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.bls.resetpwd.TokenMail;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Collection;

@Singleton
@Path("/users/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao<User<String>> userDao;
    private final ResetPasswordTokenDao<ResetPasswordToken<String>> tokenDao;

    @Inject
    public UserResource(UserDao userDao, ResetPasswordTokenDao tokenDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public User getById(@PathParam("id") String id) {
        final Optional<User<String>> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException(String.format("User with id: %s not found", id));
        }
        return user.get();
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public User<String> create(@Valid final User<String> userWithPlaintextPassword) {
        final String hashedPassword = BasicAuthenticator.generateSafeHash(userWithPlaintextPassword.getPassword());
        final User<String> userWithHashedPassword = userWithPlaintextPassword.createUserWithHashedPassword(hashedPassword);
        return userDao.create(userWithHashedPassword);
    }

    @DELETE
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void removeById(@Auth String foo, @PathParam("id") String id) {
        userDao.deleteById(id);
    }

    // TODO remove token return 
    @Path("/changepassword")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public String resetPassword(@PathParam("id") final String id) throws MessagingException {
        Optional<User<String>> user = userDao.findById(id);
        if (user == null) throw new NotFoundException();

        ResetPasswordToken token = new ResetPasswordToken();
        tokenDao.create(token);
        String userEmail = user.get().getEmail();
//        new TokenMail(token).to(userEmail);
        return token.getToken();
    }

    @Path("/changepassword/{token}")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void changePassword(@PathParam("id") final String id, @PathParam("token") final String token, 
                               final String plaintextPassword) 
            throws MessagingException {
        
        Optional<User<String>> user = userDao.findById(id);
        if (!user.isPresent()) throw new NotFoundException();
        if (!tokenDao.read(token).isPresent()) throw new NotAllowedException("Wrong token");
        String newHashedPassword = BasicAuthenticator.generateSafeHash(plaintextPassword);
        user.get().setPassword(newHashedPassword);
    }

    // TODO I'm temp
    @Path("/tokens")
    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<ResetPasswordToken<String>> getAllTokens() {
        return tokenDao.findAll();
    }
}
