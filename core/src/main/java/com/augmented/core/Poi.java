package com.augmented.core;


public abstract class Poi {

    private String name;

    private Tag tag;

    private Location location;

    public abstract String getId();

    public abstract void setId(String id);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
