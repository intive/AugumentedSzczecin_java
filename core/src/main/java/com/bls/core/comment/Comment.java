package com.bls.core.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    private final String username;
    private final String content;
    private final int rating;
    private final String dateOfcomment;

    @JsonCreator
    public Comment(@JsonProperty("username") final String username,
            @JsonProperty("content") final String content,
            @JsonProperty("rating") final int rating,
            @JsonProperty("date") final String dateOfcomment) {
        this.username = username;
        this.content = content;
        this.rating = rating;
        this.dateOfcomment = dateOfcomment;
    }

    public String getUsername() {
        return this.username;
    }

    public String getContent() {
        return this.content;
    }

    public int getRating() {
        return this.rating;
    }

    public String getDateofComment() {
        return this.dateOfcomment;
    }
}
