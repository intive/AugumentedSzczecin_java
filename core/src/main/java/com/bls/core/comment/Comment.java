package com.bls.core.comment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO add javadoc
@JsonInclude(Include.NON_EMPTY)
public class Comment {

    private final String content;
    private final int rating;
    private final Date created;

    @JsonCreator
    public Comment(@JsonProperty("content") final String content,
            @JsonProperty("rating") final int rating,
            @JsonProperty(value = "date", required = false) final Date created) {
        this.content = content;
        this.rating = rating;
        this.created = created != null ? created : new Date();
    }

    public String getContent() {
        return this.content;
    }

    public int getRating() {
        return this.rating;
    }

    public Date getDateofComment() {
        return this.created;
    }
}
