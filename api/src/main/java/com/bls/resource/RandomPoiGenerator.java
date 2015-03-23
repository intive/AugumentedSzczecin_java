package com.bls.resource;

import java.util.Collection;
import java.util.Random;

import com.bls.core.poi.Location;
import com.bls.core.poi.Poi;
import com.bls.core.poi.Tag;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;

@Singleton
public class RandomPoiGenerator {

<<<<<<< HEAD
    public Collection<Poi<String>> generate(final int howMany) {
        final Collection<Poi<String>> pois = Lists.newArrayListWithCapacity(howMany);
=======
    public Collection<Poi> generate(final int howMany) {
        final Collection<Poi> pois = Lists.newArrayListWithCapacity(howMany);
>>>>>>> master
        for (int i = 0; i < howMany; i++) {
            pois.add(generateRandomPoi());
        }
        return pois;
    }

<<<<<<< HEAD
    private Poi<String> generateRandomPoi() {
=======
    private Poi generateRandomPoi() {
>>>>>>> master
        final String name = generateRandomName();
        final Tag tag = generateRandomTag();
        final Location location = generateRandomLocation();
        return new Poi<>(null, name, tag, location);
    }

    private Location generateRandomLocation() {
        final long latitude = new Random().nextLong();
        final long longitude = new Random().nextLong();
        return new Location(latitude, longitude);
    }

    private Tag generateRandomTag() {
        final Tag[] tags = Tag.values();
        return Lists.newArrayList(tags).get(new Random().nextInt(tags.length));
    }

    private String generateRandomName() {return "thug_" + new Random().nextInt(1000);}
}