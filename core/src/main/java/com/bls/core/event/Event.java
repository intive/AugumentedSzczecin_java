package com.bls.core.event;

import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO add javadoc
@JsonInclude(Include.NON_EMPTY)
public class Event<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String description;
    @NotEmpty
    private final Location location;

    @JsonCreator
    public Event(@JsonProperty(value = "id", required = false) final K id,
            @JsonProperty("description") final String description,
            @JsonProperty("location") final Location location) {
        super(id);
        this.description = description;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }
}
