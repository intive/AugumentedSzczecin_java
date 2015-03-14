package com.bls.core.poi;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Poi<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String name;
    @NotNull
    private final Location location;
    private final Tag tag;

    @JsonCreator
    public Poi(@JsonProperty(value = "id", required = false) final K id,
            @JsonProperty("name") final String name,
            @JsonProperty("tag") final Tag tag,
            @JsonProperty("location") final Location location) {
        super(id);
        this.name = name;
        this.tag = tag;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Tag getTag() {
        return tag;
    }

    public Location getLocation() {
        return location;
    }
}
