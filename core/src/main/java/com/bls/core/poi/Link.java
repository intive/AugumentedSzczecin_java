package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Link<K> extends IdentifiableEntity<K> {

    private final String link;
    private final String name;

    @JsonCreator
    public Link(@JsonProperty(value = "id", required = false) final K id,
                @JsonProperty("name")final String name,
                @JsonProperty("link") final String link) {
        super(id);
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
