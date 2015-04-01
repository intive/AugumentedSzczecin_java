package com.bls.core.person;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Some person who is in some place on map (static)
 *
 * @param <K> Key type for person entity
 */
@JsonInclude(Include.NON_EMPTY)
public class Person<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String name;
    @NotEmpty
    private final String surname;
    @NotNull
    private final Location location;
    @NotNull
    private String ownerid;

    @JsonCreator
    public Person(@JsonProperty(value = "id", required = false) final K id,
                  @JsonProperty("name") final String name,
                  @JsonProperty("surname") final String surname,
                  @JsonProperty("location") final Location location,
                  @JsonProperty("ownerid") final String ownerid) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.location = location;
        this.ownerid = ownerid;
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

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }
}
