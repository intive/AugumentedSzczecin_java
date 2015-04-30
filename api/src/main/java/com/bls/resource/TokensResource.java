package com.bls.resource;

import com.bls.core.resetpwd.ResetPasswordToken;
import com.bls.dao.ResetPasswordTokenDao;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

// TODO remove me
/**
 *  Temporary resource for checking currently valid ResetPasswordToken
 */
@Singleton
@Path("/tokens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TokensResource {
    private final ResetPasswordTokenDao<ResetPasswordToken<String>> tokenDao;

    @Inject
    public TokensResource(ResetPasswordTokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    @GET
    @UnitOfWork
    @Timed
    @ExceptionMetered
    public Collection<ResetPasswordToken<String>> getAllTokens() {
        return tokenDao.findAll();
    }
}
