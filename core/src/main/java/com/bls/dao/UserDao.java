package com.bls.dao;

import com.bls.core.IdentifiableEntity;
import com.bls.core.user.User;
import javassist.NotFoundException;

/**
 * Created by Marcin Podlodowski on 3/15/15.
 */
public interface UserDao<E extends IdentifiableEntity, K> extends CommonDao<E, K> {

    User findByEmail(final String email) throws NotFoundException;
    
    void deleteByEmail(final String email);

    User add(final User user) throws Exception;
}
