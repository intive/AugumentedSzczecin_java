package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Paweł on 2015-03-25.
 */
public class Event<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String description;
    @NotEmpty
    private final Location location;

    @JsonCreator
    public Event(@JsonProperty("description")String description,
                  @JsonProperty("location")Location location,
                  @JsonProperty(value = "id", required = false) final K id) {
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
