package com.bls.resource;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.user.ResetPasswordToken;
import com.bls.core.user.User;
import com.bls.dao.ResetPasswordTokenDao;
import com.bls.dao.UserDao;
import com.bls.resetpwd.ResetPasswordTokenBuilder;
import com.bls.resetpwd.ResetPasswordTokenConfiguration;
import com.bls.resetpwd.TokenMail;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;

/**
 * Created by Marcin Podlodowski on 28.04.15.
 */
@Singleton
@Path("/users/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.TEXT_PLAIN)
public class ResetPasswordResource {
    private final UserDao<User<String>> userDao;
    private final ResetPasswordTokenDao<ResetPasswordToken<String>> tokenDao;
    private final ResetPasswordTokenConfiguration tokenConfig;

    @Inject
    public ResetPasswordResource(final UserDao userDao, 
                                 final ResetPasswordTokenDao tokenDao, 
                                 final ResetPasswordTokenConfiguration tokenConfig) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
        this.tokenConfig = tokenConfig;
    }
    
    @Path("/resetpassword")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void resetPassword(@PathParam("id") final String id) throws MessagingException, UnsupportedEncodingException {
        Optional<User<String>> user = userDao.findById(id);
        if (!user.isPresent()) throw new BadRequestException("User not found");

        ResetPasswordToken token = new ResetPasswordTokenBuilder().forDuration(tokenConfig.getExpiration());
        tokenDao.create(token);
        String userEmail = user.get().getEmail();
        new TokenMail(token).to(userEmail);
    }

    @Path("/changepassword/{token}")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void changePassword(@PathParam("id") final String id, @PathParam("token") final String tokenString,
                               final String plaintextPassword)
            throws MessagingException {

        Optional<User<String>> user = userDao.findById(id);
        if (!user.isPresent()) throw new BadRequestException("User not found");
        Optional<ResetPasswordToken> token;
        token = tokenDao.read(tokenString);
        if (!token.isPresent()) throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        String newHashedPassword = BasicAuthenticator.generateSafeHash(plaintextPassword);
        user.get().setPassword(newHashedPassword);
        userDao.update(user.get());
        tokenDao.expire(token.get());
    }
    
}
