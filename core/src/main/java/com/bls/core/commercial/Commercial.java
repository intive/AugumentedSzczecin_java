package com.bls.core.commercial;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;


public class Commercial<K> extends IdentifiableEntity<K> {


    @NotEmpty
    private final String links;

    @JsonCreator
    public Commercial(@JsonProperty(value = "id", required = false) final K id,
                 @JsonProperty("links") final String links) {
        super(id);
        this.links = links;
    }

    public String getLinks() {return links; }
}