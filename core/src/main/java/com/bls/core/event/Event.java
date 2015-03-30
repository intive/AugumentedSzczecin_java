package com.bls.core.event;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Event<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String description;
    @NotEmpty
    private final Location location;

    @JsonCreator
    public Event(@JsonProperty(value = "id", required = false) final K id,
                @JsonProperty("description")String description,
                @JsonProperty("location")Location location) {
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
