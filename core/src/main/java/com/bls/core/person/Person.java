package com.bls.core.person;

import javax.validation.constraints.NotNull;

import com.bls.core.comment.Comment;
import com.bls.core.poi.*;
import com.bls.core.price.PriceList;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.bls.core.geo.Location;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Collection;

/**
 * Some person who is in some place on map (static)
 *
 * @param <K> Key type for person entity
 */
@JsonInclude(Include.NON_EMPTY)
@JsonTypeName("person")
public class Person<K> extends Poi<K> {
    private static final String category = String.valueOf(Category.PERSON);

    private final String firstname;
    private final String lastname;
    
    @JsonCreator
    public Person(@JsonProperty(value = "id", required = false) final K id,
//               @JsonProperty("category") final String category,
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
        super(id, category, isPublic, name, location, address, owner, tags, comments, media, openingDaysAndHours, priceList);
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
