package com.bls.resource;

import java.util.Collection;

import com.bls.core.event.Event;
import com.bls.core.person.Person;
import com.bls.core.place.Place;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

@JsonInclude(Include.NON_EMPTY)
public class SearchingResults {

    // TODO add some pag11ing, compute some ratios...
    @JsonProperty
    private Collection<Event> events = Lists.newArrayList();
    @JsonProperty
    private Collection<Person> people = Lists.newArrayList();
    @JsonProperty
    private Collection<Place> places = Lists.newArrayList();

    public void putEvents(final Collection<Event> events) {
        this.events.addAll(events);
    }

    public void putPerson(final Collection<Person> people) {
        this.people.addAll(people);
    }

    public void putPlaces(final Collection<Place> places) {
        this.places.addAll(places);
    }
}
