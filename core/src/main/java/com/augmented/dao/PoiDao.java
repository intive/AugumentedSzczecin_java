package com.augmented.dao;


import com.augmented.core.Poi;
import com.augmented.core.Tag;

import java.util.List;

public interface PoiDao {

    public void clearAll();

    public List<Poi> findAllPoi();

    public String savePoi(final Poi poi);

    public Poi getbyId(final String id);

    public List<Poi> findByTag(final Tag tag);

    public List<Poi> findByFieldAndValue(final String field, final String value);
}
