package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.DayOfWeek;

/**
 * MONDAY 10:00 - 18:00
 */
@JsonInclude(Include.NON_EMPTY)
public class OpeningHours {
    private final String TIME_REGEX = "(([01][0-9])|(2[0-3])):[0-5][0-9]";

    private final DayOfWeek day;
    @NotNull
    @Pattern(regexp = TIME_REGEX)
    private final String open;
    @NotNull
    @Pattern(regexp = TIME_REGEX)
    private final String close;

    @JsonCreator
    public OpeningHours(@JsonProperty("day") final DayOfWeek day,
            @JsonProperty("open") final String open,
            @JsonProperty("close") final String close) {
        this.day = day;
        this.open = open;
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public DayOfWeek getDay() {
        return day;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OpeningHours{")
                .append("day=").append(day).append(" (")
                .append(open)
                .append(" - ")
                .append(close)
                .append(")}").toString();
    }
}
