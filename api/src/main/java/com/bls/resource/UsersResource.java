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

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.User;
import com.bls.core.views.Views;
import com.bls.dao.ResetPasswordTokenDao;
import com.bls.dao.UserDao;
import com.bls.core.resetpwd.ResetPasswordToken;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    private final UserDao<User<String>> userDao;
    private final ResetPasswordTokenDao<ResetPasswordToken<String>> tokenDao;

    @Inject
    public UsersResource(UserDao userDao, ResetPasswordTokenDao tokenDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
    }

    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    @JsonView(Views.Public.class)
    public void create(@Valid final User<String> userWithPlaintextPassword) {
        final String hashedPassword = BasicAuthenticator.generateSafeHash(userWithPlaintextPassword.getPassword());
        final User<String> userWithHashedPassword = userWithPlaintextPassword.createUserWithHashedPassword(hashedPassword);
        userDao.create(userWithHashedPassword);
    }
}
