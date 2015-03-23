package com.bls.auth.basic;

<<<<<<< HEAD
=======
import org.mindrot.jbcrypt.BCrypt;

>>>>>>> master
import com.bls.core.user.User;
import com.bls.dao.UserDao;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
<<<<<<< HEAD
=======
import io.dropwizard.hibernate.UnitOfWork;

import static com.bls.AugmentedConfiguration.PW_HASH_SECURITY_LEVEL;
>>>>>>> master

/**
 * Basic Authenticator class using plaintext credentials
 * Used to authenticate resources with @Auth annotation
<<<<<<< HEAD
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

=======
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
>>>>>>> master
