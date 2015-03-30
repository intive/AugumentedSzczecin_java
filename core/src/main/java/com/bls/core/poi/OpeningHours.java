package com.bls.core.poi;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MONDAY 10:00 - 18:00
 */
public class OpeningHours {

    @Range(min = DateTimeConstants.MONDAY, max = DateTimeConstants.SUNDAY)
    private final int dayOfWeek;
    @NotNull
    private final DateTime opening;
    @NotNull
    private final DateTime closing;

    @JsonCreator
    public OpeningHours(@JsonProperty("dayOfWeek") final int dayOfWeek,
            @JsonProperty("opening") final DateTime opening,
            @JsonProperty("closing") final DateTime closing) {
        this.dayOfWeek = dayOfWeek;
        this.opening = opening;
        this.closing = closing;
    }

    public DateTime getOpening() {
        return opening;
    }

    public DateTime getClosing() {
        return closing;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OpeningHours{")
                .append("dayOfWeek=").append(dayOfWeek).append(" (")
                .append(opening.toString("HH:MM"))
                .append(" - ")
                .append(closing.toString("HH:MM"))
                .append(")}").toString();
    }
}
