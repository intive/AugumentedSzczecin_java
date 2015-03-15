package com.bls.auth.basic;

import org.mindrot.jbcrypt.BCrypt;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import static com.bls.AugmentedConfiguration.PW_HASH_SECURITY_LEVEL;

/**
 * Basic Authenticator class using plaintext credentials
 * Used to authenticate resources with @Auth annotation
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User<String>> {

    private final UserDao<User<String>, String> userDao;

    public BasicAuthenticator(UserDao<User<String>, String> userDao) {this.userDao = userDao;}

    public Optional<User<String>> authenticate(final BasicCredentials basicCredentials) throws AuthenticationException {

        String email = basicCredentials.getUsername();
        String plaintextPassword = basicCredentials.getPassword();

        return authenticated(email, plaintextPassword);
    }

    private Optional<User<String>> authenticated(String email, String plaintextPassword) throws AuthenticationException {

        final Optional<User<String>> user = userDao.findByEmail(email);
        if (user.isPresent() && isMatched(plaintextPassword, user.get().getPassword())) {
            return user;
        }
        throw new AuthenticationException("Invalid credentials");
    }

    private boolean isMatched(final String plaintextPassword, final String hashed) {
        return BCrypt.checkpw(plaintextPassword, hashed);
    }

    public static String generateSafeHash(final String plaintextPassword) {
        return BCrypt.hashpw(plaintextPassword, BCrypt.gensalt(PW_HASH_SECURITY_LEVEL));
    }
}

