package com.bls.auth.basic;

import org.mindrot.jbcrypt.BCrypt;

import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

import static com.bls.AugmentedConfiguration.PW_HASH_SECURITY_LEVEL;

/**
 * Basic Authenticator class using plaintext credentials
 * Used to authenticate resources with @Auth annotation
 *
 * @param K entity key type for User
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDao<User> userDao;

    public BasicAuthenticator(UserDao<User> userDao) {this.userDao = userDao;}

    public static String generateSafeHash(final String plaintextPassword) {
        return BCrypt.hashpw(plaintextPassword, BCrypt.gensalt(PW_HASH_SECURITY_LEVEL));
    }

    private static boolean isMatched(final String plaintextPassword, final String hashed) {
        return BCrypt.checkpw(plaintextPassword, hashed);
    }

    @UnitOfWork
    public Optional<User> authenticate(final BasicCredentials basicCredentials) throws AuthenticationException {

        String email = basicCredentials.getUsername();
        String plaintextPassword = basicCredentials.getPassword();

        final Optional<User> user = userDao.findByEmail(email);
        if (user.isPresent() && isMatched(plaintextPassword, user.get().getPassword())) {
            return user;
        }
        return Optional.absent();
    }
}
