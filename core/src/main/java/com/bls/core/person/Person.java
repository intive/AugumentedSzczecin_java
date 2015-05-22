package com.bls.core.person;

import com.bls.core.geo.Location;
import com.bls.core.poi.Address;
import com.bls.core.poi.Category;
import com.bls.core.poi.OpeningHours;
import com.bls.core.poi.Poi;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * Some person who is in some place on map (static)
 *
 * @param <K> Key type for person entity
 */
@JsonInclude(Include.NON_EMPTY)
public class Person<K> extends Poi<K> {
    private static final Category category = Category.PERSON;
    
    @JsonCreator
    public Person(@JsonProperty(value = "id", required = false) final K id,
                 @JsonProperty("name") final String name,
                 @JsonProperty("description") final String description,
                 @JsonProperty("location") final Location location,
                 @JsonProperty("address") final Address address,
                 @JsonProperty("owner") final User owner,
                 @JsonProperty("tags") final Collection<String> tags,
                 @JsonProperty("media") final Collection<String> media,
                 @JsonProperty("openingDaysAndHours") final Collection<OpeningHours> openingDaysAndHours,
                 @JsonProperty("price") final Float price,
                 @JsonProperty("wiki") final String wiki,
                 @JsonProperty("fanpage") final String fanpage,
                 @JsonProperty("www") final String www,
                 @JsonProperty("phone") final String phone) {
        super(id, name, description, location, address, owner, tags, media, openingDaysAndHours, price,
                wiki, fanpage, www, phone);
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
