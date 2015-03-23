package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpeningHoursForADay<K> extends IdentifiableEntity<K> {

    private final String startHour;
    private final String stopHour;
    private final Day day;

    @JsonCreator
    public OpeningHoursForADay(@JsonProperty(value = "id", required = false) final K id,
                                @JsonProperty("day") final Day day,
                                @JsonProperty("startHour") final String startHour,
                                @JsonProperty("stopHour") final String stopHour) {
        super(id);
        this.day = day;
        this.startHour = startHour;
        this.stopHour = stopHour;
    }

    public Day getDay() {
        return day;
    }

    public String getStartHour() {
        return this.startHour;
    }

    public String getStopHour() {
        return this.stopHour;
    }
}
