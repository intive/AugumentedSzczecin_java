package com.bls.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class PagingConfiguration extends Configuration {
    private final int pageSize;

    public PagingConfiguration(@JsonProperty("pageSize") final int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
}
