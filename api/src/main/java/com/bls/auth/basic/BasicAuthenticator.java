package com.bls.auth.basic;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Basic Authenticator class using plaintext credentials
 * Used to authenticate resources with @Auth annotation
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User<String>> {

    // TODO we prefer constructor injection, immutability, use types here, userDao instead of commonDao
    private final UserDao<User<String>, String> userDao;

    public BasicAuthenticator(UserDao<User<String>, String> userDao) {this.userDao = userDao;}

    public Optional<User<String>> authenticate(final BasicCredentials basicCredentials) throws AuthenticationException {

        String emailProvided = basicCredentials.getUsername();
        String passwordProvided = basicCredentials.getPassword();

        return getUserForCredentials(emailProvided, passwordProvided);
    }

    private Optional<User<String>> getUserForCredentials(String emailProvided, String passwordProvided) throws AuthenticationException {

        final Optional<User<String>> user = userDao.findByEmail(emailProvided);
        if (user.isPresent()) {
            return user;
        }
        throw new AuthenticationException("Invalid credentials");
    }
}

