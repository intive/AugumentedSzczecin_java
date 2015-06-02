package com.bls.dao;

import com.bls.core.poi.Poi;

/**
 * User specific DAO operation
 *
 * @param <E> Entity type
 */
public interface EventDao<E extends Poi> extends PoiDao<E> {
}
