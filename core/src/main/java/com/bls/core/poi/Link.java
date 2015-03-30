package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

    private final String link;
    private final String name;

    @JsonCreator
    public Link(@JsonProperty("name") final String name, @JsonProperty("link") final String link) {
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public String getName() {
        return this.name;
    }
}
