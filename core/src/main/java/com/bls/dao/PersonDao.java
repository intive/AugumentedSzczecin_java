package com.bls.dao;

import com.bls.core.Identifiable;

/**
 * Created by Paweł on 2015-03-25.
 */
public interface PersonDao<E extends Identifiable> {

    E add(final E entity);
    E update(final E entity);
    E remove(final E entity);


}
