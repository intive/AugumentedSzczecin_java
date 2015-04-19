package com.bls.core.place;


import com.bls.core.comment.Comment;
import com.bls.core.geo.Location;
import com.bls.core.poi.*;
import com.bls.core.price.PriceList;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;

/**
 * An place with some specific location
 *
 * @param <K> Key type for Place entity
 */
@JsonInclude(Include.NON_EMPTY)
public class Place <K> extends Poi<K> {

    private static final Category category = Category.PLACE;
    private final String description;

    @JsonCreator
    public Place(@JsonProperty(value = "id", required = false) final K id,
                 @JsonProperty("isPublic") final Boolean isPublic,
                 @JsonProperty("name") final String name,
                 @JsonProperty("location") final Location location,
                 @JsonProperty("address") final Address address,
                 @JsonProperty("owner") final User owner,
                 @JsonProperty("tags") final Collection<String> tags,
                 @JsonProperty("comments") final Collection<Comment> comments,
                 @JsonProperty("media") final Collection<Media> media,
                 @JsonProperty("openingDaysAndHours") final Collection<OpeningHours> openingDaysAndHours,
                 @JsonProperty("priceList") final PriceList priceList,
                 @JsonProperty("description") final String description) {
        super(id, isPublic, name, location, address, owner, tags, comments, media, openingDaysAndHours, priceList);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
