package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.bls.core.comment.Comment;
import com.bls.core.geo.Location;
import com.bls.core.price.PriceList;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.ImmutableList;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Point Of Interest
 *
 * @param <K> key type
 */
@JsonInclude(Include.NON_EMPTY)
public abstract class Poi<K> extends IdentifiableEntity<K> {

    @NotNull
    protected final Boolean isPublic;
    // TODO add validation: !public = NotNull
    @JsonIgnore
    protected final User owner;
    @NotNull
    protected final Location location;
    protected final String name;
    protected final Address address;
    protected final Collection<String> tags;
    protected final Collection<Media> media;
    protected final Collection<Comment> comments;
    protected final Collection<OpeningHours> openingDaysAndHours;
    protected final PriceList priceList;
    
    @JsonCreator
    public Poi(@JsonProperty(value = "id", required = false) final K id,
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
        super(id);
        this.isPublic = isPublic;
        this.name = name;
        this.location = location;
        this.address = address;
        this.owner = owner;
        this.priceList = priceList;

        this.tags = tags != null ? ImmutableList.copyOf(tags) : ImmutableList.of();
        this.comments = comments != null ? ImmutableList.copyOf(comments) : ImmutableList.of();
        this.media = media != null ? ImmutableList.copyOf(media) : ImmutableList.of();
        this.openingDaysAndHours = openingDaysAndHours != null ? ImmutableList.copyOf(openingDaysAndHours) : ImmutableList.of();
    }

    // Use this to hide property in deserialized json
//    @JsonIgnore
    public abstract Category getCategory();

    public Boolean getIsPublic() {
        return isPublic;
    }

    public User getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public Collection<Media> getMedia() {
        return media;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public Collection<OpeningHours> getOpeningDaysAndHours() {
        return openingDaysAndHours;
    }

    public PriceList getPriceList() {
        return priceList;
    }
}
