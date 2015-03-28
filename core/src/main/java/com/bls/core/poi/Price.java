package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Price {

    private final String thing;
    private final BigDecimal price;

    @JsonCreator
    public Price (@JsonProperty("thing") final String thing,
                  @JsonProperty("price") final BigDecimal price) {
        this.thing = thing;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getThing() {
        return this.thing;
    }
}
