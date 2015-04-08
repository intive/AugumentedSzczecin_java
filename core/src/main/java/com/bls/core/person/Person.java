package com.bls.core.person;

import com.bls.core.comment.Comment;
import com.bls.core.geo.Location;
import com.bls.core.poi.*;
import com.bls.core.price.PriceList;
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

    private final String firstname;
    private final String lastname;
    
    @JsonCreator
    public Person(@JsonProperty(value = "id", required = false) final K id,
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
               @JsonProperty("firstname") final String firstname,
               @JsonProperty("lastname") final String lastname) {
        super(id, isPublic, name, location, address, owner, tags, comments, media, openingDaysAndHours, priceList);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
