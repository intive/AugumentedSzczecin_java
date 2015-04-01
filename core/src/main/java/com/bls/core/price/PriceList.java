package com.bls.core.price;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

/** Named price list */
@JsonInclude(Include.NON_EMPTY)
public class PriceList {

    private final String name;
    private final Collection<PriceItem> items;

    @JsonCreator
    public PriceList(@JsonProperty("items") final Collection<PriceItem> items, @JsonProperty("name") final String name) {
        this.name = name;
        this.items = items != null ? ImmutableList.copyOf(items) : ImmutableList.of();
    }

    public Collection<PriceItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
}
