package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Paweł on 2015-03-25.
 */
public class Person<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private final String name;
    @NotEmpty
    private final String surname;
    @NotEmpty
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
