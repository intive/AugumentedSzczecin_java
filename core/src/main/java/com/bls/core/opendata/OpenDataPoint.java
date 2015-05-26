package com.bls.core.opendata;


import com.bls.core.geo.Location;
import com.bls.core.place.Place;
import com.bls.core.poi.Address;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataPoint {

    private final String coll;
    private final String name;
    private final String description;
    private final Location location;
    private final Cat cat;
    private final Address address;
    private final Place.Subcategory subcategory;

    @JsonCreator
    public OpenDataPoint(@JsonProperty("coll") final String coll,
                         @JsonProperty("name") final String name,
                         @JsonProperty("descr") final String description,
                         @JsonProperty("Loc") final Loc loc,
                         @JsonProperty("Cat") final Cat cat,
                         @JsonProperty("Addr") final Addr addr){
        this.coll = coll;
        this.name = name;
        this.description = description;
        this.location = new Location(loc.getLon(), loc.getLat());
        this.cat = cat;
        this.address = new Address(addr.getCity(), addr.getStreet(), addr.getPostcode(), null, null);
        this.subcategory = Place.Subcategory.MONUMENT;
    }

    public String getColl(){
        return coll;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Location getLocation(){
        return location;
    }

    public Cat getCat(){
        return cat;
    }

    public Address getAddress(){
        return address;
    }

    public Place.Subcategory getSubcategory(){ return subcategory; }
}
