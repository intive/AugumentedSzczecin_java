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

/**
 *  Basic Authenticator class using plaintext credentials
 *  Used to authenticate resources with @Auth annotation
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    // TODO we prefer constructor injection, immutability, use types here, userDao instead of commonDao
    private final CommonDao<User<String>, String> userDao;

    @Inject
    public BasicAuthenticator(@Named("user") final CommonDao userDao) {this.userDao = userDao;}

    public Optional<User> authenticate(final BasicCredentials basicCredentials) throws AuthenticationException {

        String emailProvided = basicCredentials.getUsername();
        String passwordProvided = basicCredentials.getPassword();

        User user = getUserForCredentials(emailProvided, passwordProvided);
        if (user != null) return Optional.of(user);

        throw new AuthenticationException("Invalid credentials");
    }

    private User getUserForCredentials(String emailProvided, String passwordProvided) {

        Collection<User<String>> userList = userDao.findAll();

        for (User u : userList) {
            if (u.getEmail().equals(emailProvided) && u.getPassword().equals(passwordProvided)) {
                return u;
            }
        }
        
        return null;
    }
    
    
}

