package com.bls.core.event;

import com.bls.core.comment.Comment;
import com.bls.core.poi.*;
import com.bls.core.price.PriceList;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

// TODO add javadoc
@JsonInclude(Include.NON_EMPTY)
@JsonTypeName("event")
public class Event<K> extends Poi<K> {
    private static final String category = String.valueOf(Category.EVENT);

    @JsonCreator
    public Event(@JsonProperty(value = "id", required = false) final K id,
                 @JsonProperty("isPublic") final Boolean isPublic,
                 @JsonProperty("name") final String name,
                 @JsonProperty("location") final Location location,
                 @JsonProperty("address") final Address address,
                 @JsonProperty("owner") final User owner,
                 @JsonProperty("tags") final Collection<String> tags,
                 @JsonProperty("comments") final Collection<Comment> comments,
                 @JsonProperty("media") final Collection<Media> media,
                 @JsonProperty("openingDaysAndHours") final Collection<OpeningHours> openingDaysAndHours,
                 @JsonProperty("priceList") final PriceList priceList) {
        super(id, category, isPublic, name, location, address, owner, tags, comments, media, openingDaysAndHours, priceList);

    }

}
