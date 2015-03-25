package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
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


    public Person(@JsonProperty("name")String name,
                  @JsonProperty("surname")String surname,
                  @JsonProperty("location")Location location,
                  final K id) {
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
