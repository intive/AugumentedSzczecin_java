package com.bls.dao;

import com.bls.core.Identifiable;
import com.bls.core.person.Person;
import com.bls.core.user.User;
import com.google.common.base.Optional;

import java.util.Collection;
import java.util.List;

/**
 * User specific DAO operation
 *
 * @param <E> Entity type
 */
public interface PersonDao<E extends Identifiable> extends CommonDao<E> {

    List<Person<String>> findByUserId(final String id);
    
}
