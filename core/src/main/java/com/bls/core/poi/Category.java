package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO add javadoc
// TODO add subcategory
@JsonInclude(Include.NON_EMPTY)
public enum Category {
    
    PERSON("person"),
    EVENT("event");
    
    private final String name;

    Category(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}