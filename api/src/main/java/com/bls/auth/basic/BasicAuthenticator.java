package com.bls.auth.basic;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

// TODO describe purpose of existence of this class
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    // TODO we prefer constructor injection, immutability, use types here, userDao instead of commonDao
    private final CommonDao<User<String>, String> userDao;

    @Inject // TODO we prefer javax package over google specific one...
    public BasicAuthenticator(@Named("user") final CommonDao userDao) {this.userDao = userDao;}

    @Override
    public Optional<User> authenticate(final BasicCredentials basicCredentials) throws AuthenticationException {

        String emailProvided = basicCredentials.getUsername();
        String passwordProvided = basicCredentials.getPassword();

        Collection<User<String>> userList = userDao.findAll();

        for (User u : userList) {
            // TODO separate concerns: extract logic for authentication to some priv. method?
            if (u.getEmail().equals(emailProvided) && u.getPassword().equals(passwordProvided)) {
                return Optional.of(u);
            }
        }

        // TODO IMHO it should throw AuthenticationException here (and return User instead of Optional<User>) -> authenticated or GTFO :)
        return Optional.absent();
    }
}