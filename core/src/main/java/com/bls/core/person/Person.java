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
    
    // FIXME: Response 400 with "Unable to process JSON message"
    // Sth wrong with ObjectMapper?
    // When using "include As.WRAPPER_OBJECT:
    // "details":"Could not resolve type id 'name' into a subtype of [simple type, class com.bls.core.event.Event]: 
    // known type ids = [event, person]"
    //
    // When using "include As.PROPERTY:
    // "Unexpected token (END_OBJECT), expected FIELD_NAME: missing property 'type' that is to contain type id  
    // (for class com.bls.core.event.Event)"
    
    // TODO: add subclass-specific fields
    
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
               @JsonProperty("priceList") final PriceList priceList) {
        super(id, isPublic, name, location, address, owner, tags, comments, media, openingDaysAndHours, priceList);
    }
}
