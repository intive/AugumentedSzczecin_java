package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO add javadoc
// TODO add subcategory
@JsonInclude(Include.NON_EMPTY)
public enum Category {
    MUSEUM, UNIVERSITY, TRAIN_STATION
}