package com.bls.core.poi;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class Poi<K> extends IdentifiableEntity<K> {

    private final Collection<Comment> comments;
    private final Collection<Link> links;
    private final Collection<Media> media;
    private final Collection<OpeningHoursForADay> openingDaysAndHours;
    private final Collection<Price> pricelist;

    private final Address address;
    private final Category category;
    private final Owner owner;
    private final String phoneNumber;
    private final String name;
    @NotNull
    private final Location location;
    private final Tag tag;
    @JsonCreator
    public Poi(@JsonProperty(value = "id", required = false) final K id,
               @JsonProperty("name") final String name,
               @JsonProperty("tag") final Tag tag,
               @JsonProperty("location") final Location location) {
        super(id);
        this.name = name;
        this.tag = tag;
        this.location = location;
        this.comments = new ArrayList<Comment>();
        this.links = new ArrayList<Link>();
        this.media = new ArrayList<Media>();
        this.openingDaysAndHours = new ArrayList<OpeningHoursForADay>();
        this.pricelist = new ArrayList<Price>();
        this.phoneNumber = "456789123";
        this.address = new Address("Poland", "Szczecin", "Krzywoustego 20", "(032)44-55-500");
        this.category = Category.valueOf("MONDAY");
        this.owner = new Owner("Tomasz Kowalski", "tkowalski@gmail.com", "555666777");
    }
    @JsonCreator
    public Poi(@JsonProperty(value = "id", required = false) final K id,
               @JsonProperty("name") final String name,
               @JsonProperty("tag") final Tag tag,
               @JsonProperty("location") final Location location,
               @JsonProperty("address") final Address address,
               @JsonProperty("category") final Category category,
               @JsonProperty("owner") final Owner owner,
               @JsonProperty("phoneNumber") final String phoneNumber,
               @JsonProperty("comments") final List<Comment> comments,
               @JsonProperty("links") final List<Link> links,
               @JsonProperty("media") final List<Media> media,
               @JsonProperty("openingDaysAndHours")final List<OpeningHoursForADay> openingDaysAndHours,
               @JsonProperty("pricelist") final List<Price> pricelist) {
        super(id);
        this.name = name;
        this.tag = tag;
        this.location = location;
        this.comments = comments;
        this.links = links;
        this.media = media;
        this.openingDaysAndHours = openingDaysAndHours;
        this.pricelist = pricelist;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.category = category;
        this.owner = owner;
    }
    public String getName() {
        return name;
    }
   public Tag getTag() {
        return tag;
    }
    public Location getLocation() {
        return location;
    }
    public Address getAddress() {
        return address;
    }
    public Category getCategory() {
        return category;
    }
    public Owner getOwner() {
        return owner;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Collection<Comment> getComments() {
        return comments;
    }
    public Collection<Link> getLinks() {
        return links;
    }
    public Collection<Media> getMedia() {
        return media;
    }
    public Collection<OpeningHoursForADay> getOpeningDaysAndHours() {
        return openingDaysAndHours;
    }
    public Collection<Price> getPriceList() {
        return pricelist;
    }
}
