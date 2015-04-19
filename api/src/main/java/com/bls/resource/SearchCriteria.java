package com.bls.resource;

import com.bls.core.geo.Location;
import com.bls.core.poi.Category;
import org.hibernate.stat.internal.CategorizedStatistics;

import java.util.List;

/**
 * Created by Marcin Podlodowski on 4/19/15.
 */
public class SearchCriteria {

    private final Location location;
    private final Long radius;
    private final List<Category> categories;

    public SearchCriteria(final Location location, 
                          final Long radius, 
                          final List<Category> categories) {
        this.location = location;
        this.radius = radius;
        this.categories = categories;
    }

    public boolean validate() {
        // TODO
        return true;
    }

    public Location getLocation() {
        return location;
    }

    public Long getRadius() {
        return radius;
    }
    
}
