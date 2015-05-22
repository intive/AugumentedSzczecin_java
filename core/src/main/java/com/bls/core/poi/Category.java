package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *  Categories of Points of Interest
 */
@JsonInclude(Include.NON_EMPTY)
public enum Category {
    
    PERSON("person"),
    EVENT("event"),
    PLACE("place");
    
    private final String name;

    Category(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}