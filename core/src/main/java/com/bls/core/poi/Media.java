package com.bls.core.poi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Named multimedia content of some type and URL
 */
public class Media {

    private final String name;
    private final String description;
    private final String url;
    private final MediaType mediaType;

    @JsonCreator
    Media(@JsonProperty("name") final String name,
            @JsonProperty("description") final String description,
            @JsonProperty("url") final String url,
            @JsonProperty("mediaType") final MediaType mediaType) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.mediaType = mediaType;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUrl() {
        return this.url;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public enum MediaType {
        PICTURE
    }
}
