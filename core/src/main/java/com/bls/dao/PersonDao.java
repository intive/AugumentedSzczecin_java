package com.bls.dao;

import com.bls.core.person.Person;
import com.bls.core.poi.Poi;

import java.util.List;

/**
 * User specific DAO operation
 *
 * @param <E> Entity type
 */
public interface PersonDao<E extends Poi> extends PoiDao<E> {

    List<Person<String>> findByUserId(final String id);
    
}
