package com.bls.core.poi;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.bls.core.IdentifiableEntity;
import com.bls.core.comment.Comment;
import com.bls.core.geo.Location;
import com.bls.core.price.PriceList;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

/**
 * Point Of Interest
 *
 * @param <K> key type
 */
@JsonInclude(Include.NON_EMPTY)
public class Poi<K> extends IdentifiableEntity<K> {

    @NotNull
    private final Boolean isPublic;
    // TODO add validation: !public = NotNull
    private final User owner;
    @NotNull
    private final Location location;
    @NotNull
    private final Category category;
    @NotNull
    private final String name;
    private final Address address;
    private final Collection<String> tags;
    private final Collection<Media> media;
    private final Collection<Comment> comments;
    private final Collection<OpeningHours> openingDaysAndHours;
    private final PriceList priceList;

    @JsonCreator
    public Poi(@JsonProperty(value = "id", required = false) final K id,
            @JsonProperty("isPublic") final Boolean isPublic,
            @JsonProperty("name") final String name,
            @JsonProperty("location") final Location location,
            @JsonProperty("address") final Address address,
            @JsonProperty("category") final Category category,
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
        this.category = category;
        this.owner = owner;
        this.priceList = priceList;

        this.tags = tags != null ? ImmutableList.copyOf(tags) : ImmutableList.of();
        this.comments = comments != null ? ImmutableList.copyOf(comments) : ImmutableList.of();
        this.media = media != null ? ImmutableList.copyOf(media) : ImmutableList.of();
        this.openingDaysAndHours = openingDaysAndHours != null ? ImmutableList.copyOf(openingDaysAndHours) : ImmutableList.of();
    }

    public Boolean isPublic() {
        return isPublic;
    }

    public User getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public Category getCategory() {
        return category;
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
