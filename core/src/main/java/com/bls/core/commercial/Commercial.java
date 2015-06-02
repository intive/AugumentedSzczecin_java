package com.bls.core.commercial;

import java.util.Collection;

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

@JsonInclude(Include.NON_EMPTY)
public class Commercial<K> extends Poi<K> {

    private static final Category category = Category.COMMERCIAL;

    @JsonCreator
    public Commercial(@JsonProperty(value = "id", required = false) final K id,
            @JsonProperty("name") final String name,
            @JsonProperty("description") final String description,
            @JsonProperty("location") final Location location,
            @JsonProperty("address") final Address address,
            @JsonProperty("owner") final User owner,
            @JsonProperty("tags") final Collection<String> tags,
            @JsonProperty("media") final Collection<String> media,
            @JsonProperty("openingDaysAndHours") final Collection<OpeningHours> openingDaysAndHours,
            @JsonProperty("paid") final Boolean paid,
            @JsonProperty("wiki") final String wiki,
            @JsonProperty("fanpage") final String fanpage,
            @JsonProperty("www") final String www,
            @JsonProperty("phone") final String phone) {
        super(id, name, description, location, address, owner, tags, media, openingDaysAndHours, paid, wiki, fanpage, www, phone);
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
