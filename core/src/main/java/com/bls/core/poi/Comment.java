package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private final String username;
    private final String content;
    private final int rating;
    private final String dateOfcomment;

    @JsonCreator
    public Comment(@JsonProperty("username") final String username,
                   @JsonProperty("content") final String content,
                   @JsonProperty("rating") final int rating,
                   @JsonProperty("rating") final String dateOfcomment) {
        this.username = username;
        this.content = content;
        this.rating = rating;
        this.dateOfcomment = dateOfcomment;
/*        final SimpleDateFormat dateOfcomment = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.dateOfcomment = dateOfcomment.format(new Date()); */
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
