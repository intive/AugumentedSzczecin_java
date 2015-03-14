package com.bls.auth.basic;


import com.bls.core.user.User;
import com.bls.dao.CommonDao;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Collection;
import java.util.List;

/**
 * Created by Marcin Podlodowski on 3/8/15.
 */
public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
    
    CommonDao commonDao;

    @Inject
    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {

        String emailProvided = basicCredentials.getUsername();
        String passwordProvided = basicCredentials.getPassword();

        // FIXME: commonDao injection failure
        
        Collection<User<String>> userList = commonDao.findAll();

        /*for (User u : userList) {
            if(u.getEmail().equals(emailProvided) && u.getPassword().equals(passwordProvided)) {
                return Optional.of(u);
            }
        }*/

        return Optional.absent();
    }
}