package com.bls.core.place;


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

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * An place with some specific location
 *
 * @param <K> Key type for Place entity
 */
@JsonInclude(Include.NON_EMPTY)
public class Place <K> extends Poi<K> {
    
    public enum Subcategory {
        SCHOOL,
        HOSPITAL,
        PARK,
        MONUMENT,
        MUSEUM,
        OFFICE,
        BUS_STATION,
        TRAIN_STATION,
        POST_OFFICE,
        CHURCH,
        COMMERCIAL
    }

    private static final Category category = Category.PLACE;

    @NotNull
    private final Subcategory subcategory;

    @JsonCreator
    public Place(@JsonProperty(value = "id", required = false) final K id,
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
                 @JsonProperty("phone") final String phone,
                 @JsonProperty("subcategory") final Subcategory subcategory) {
        super(id, name, description, location, address, owner, tags, media, openingDaysAndHours, price, 
                wiki, fanpage, www, phone);
        this.subcategory = subcategory;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

}
