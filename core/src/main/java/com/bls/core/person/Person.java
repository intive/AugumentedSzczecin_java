package com.bls.core.person;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Some person who is in some place on map (static)
 *
 * @param <K> Key type for person entity
 */
public class Person<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String name;
    @NotEmpty
    private final String surname;
    @NotNull
    private final Location location;

    @JsonCreator
    public Person(@JsonProperty(value = "id", required = false) final K id,
            @JsonProperty("name") final String name,
            @JsonProperty("surname") final String surname,
            @JsonProperty("location") final Location location) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Location getLocation() {
        return location;
    }
}
