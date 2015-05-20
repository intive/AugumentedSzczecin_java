package com.bls.resource;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.MessagingException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bls.auth.basic.BasicAuthenticator;
import com.bls.core.resetpwd.ResetPasswordToken;
import com.bls.core.resetpwd.ResetPasswordTokenConfiguration;
import com.bls.core.user.User;
import com.bls.dao.ResetPasswordTokenDao;
import com.bls.dao.UserDao;
import com.bls.resetpwd.TokenMail;
import com.bls.resetpwd.TokenSendService;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * Request password reset token and change password for User (identified by id or email)
 */
@Singleton
@Path("/users/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResetPasswordResource {

    private final UserDao userDao;
    private final ResetPasswordTokenDao tokenDao;
    private final ResetPasswordTokenConfiguration tokenConfig;

    @Inject
    public ResetPasswordResource(final UserDao userDao,
            final ResetPasswordTokenDao tokenDao,
            final ResetPasswordTokenConfiguration tokenConfig) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
        this.tokenConfig = tokenConfig;
    }

    @Path("{id}/resetPassword")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void resetPassword(@PathParam("id") final String id) throws MessagingException, UnsupportedEncodingException {
        resetPassword(userDao.findById(id));
    }

    @Path("/email/{email}/resetPassword")
    @POST
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void resetPasswordByEmail(@PathParam("email") final String email) throws MessagingException, UnsupportedEncodingException {
        resetPassword(userDao.findByEmail(email));
    }

    private void resetPassword(final Optional<User<String>> user) throws UnsupportedEncodingException, MessagingException {
        if (!user.isPresent()) {
            throw new BadRequestException("User not found");
        }
        ResetPasswordToken token = new ResetPasswordToken(tokenConfig);
        tokenDao.create(token);

        TokenSendService tokenSendService = new TokenMail(token);
        tokenSendService.sendTo(user.get());
    }

    @Path("/{id}/changePassword")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void changePassword(@PathParam("id") final String id,
            @QueryParam("t") final String tokenString,
            final String password) throws MessagingException {

        changePassword(tokenString, password, userDao.findById(id));
    }

    @Path("/email/{email}/changePassword")
    @PUT
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public void changePasswordByEmail(@PathParam("email") final String email,
            @QueryParam("t") final String tokenString,
            final String password) throws MessagingException {

        changePassword(tokenString, password, userDao.findByEmail(email));
    }

    private void changePassword(final @QueryParam("t") String tokenString, final String password, final Optional<User<String>> user) {
        Optional<ResetPasswordToken> token = tokenDao.read(tokenString);
        if (!user.isPresent()) {
            throw new BadRequestException("User not found");
        }
        if (!token.isPresent()) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        String newHashedPassword = BasicAuthenticator.generateSafeHash(password);
        user.get().setPassword(newHashedPassword);

        userDao.update(user.get());
        tokenDao.expire(token.get());
    }
}
