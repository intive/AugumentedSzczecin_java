package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.bls.core.comment.Comment;
import com.bls.core.geo.Location;
import com.bls.core.price.PriceList;
import com.bls.core.user.User;
import com.bls.core.views.Views;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.ImmutableList;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collection;

/**
 * Point Of Interest
 *
 * @param <K> key type
 */
@JsonInclude(Include.NON_EMPTY)
public abstract class Poi<K> extends IdentifiableEntity<K> {

    @JsonView(Views.Private.class)
    protected User owner;
    
    @NotNull
    @Size(min=1, max=50)
    @Pattern(regexp = "^[\\p{L}a0-9 ]+$")
    protected final String name;
    
    @NotNull
    @Size(min=1, max=500)
    protected final String description;
    
    @Valid
    @NotNull
    protected final Location location;
    
    @Valid
    @NotNull
    protected final Address address;
    
    @Valid
    protected final Collection<String> tags;
    
    @Size(max=100)
    protected final String www;

    @Pattern(regexp = "\\d{1,21}")
    protected final String phone;
    
    @Size(max=100)
    protected final String wiki;
    
    @Size(max=100)
    protected final String fanpage;
    
    @Size(max=4)
    protected final Collection<String> media;
    
    @Valid
    protected final Collection<OpeningHours> opening;
    
    protected final Boolean paid;
    
    @JsonCreator
    public Poi(@JsonProperty(value = "id", required = false) final K id,
               @JsonProperty("name") final String name,
               @JsonProperty("description") final String description,
               @JsonProperty("location") final Location location,
               @JsonProperty("address") final Address address,
               @JsonProperty("owner") final User owner,
               @JsonProperty("tags") final Collection<String> tags,
               @JsonProperty("media") final Collection<String> media,
               @JsonProperty("opening") final Collection<OpeningHours> opening,
               @JsonProperty("paid") final Boolean paid,
               @JsonProperty("wiki") final String wiki,
               @JsonProperty("fanpage") final String fanpage,
               @JsonProperty("www") final String www,
               @JsonProperty("phone") final String phone) {
        super(id);
        this.name = name;
        this.description = description;
        this.location = location;
        this.address = address;
        this.owner = owner;
        this.paid = paid;
        this.wiki = wiki;
        this.fanpage = fanpage;
        this.www = www;
        this.phone = phone;

        this.tags = tags != null ? ImmutableList.copyOf(tags) : ImmutableList.of();
        this.media = media != null ? ImmutableList.copyOf(media) : ImmutableList.of();
        this.opening = opening != null ? ImmutableList.copyOf(opening) : ImmutableList.of();
    }

    public abstract Category getCategory();

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

    public Collection<String> getMedia() {
        return media;
    }

    public Collection<OpeningHours> getOpening() {
        return opening;
    }

    public Boolean getPaid() {
        return paid;
    }

    public String getDescription() {
        return description;
    }

    public String getWww() {
        return www;
    }

    public String getPhone() {
        return phone;
    }

    public String getWiki() {
        return wiki;
    }

    public String getFanpage() {
        return fanpage;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
