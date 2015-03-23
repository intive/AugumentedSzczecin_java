package com.bls.core.poi;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Media <K> extends IdentifiableEntity<K> {

    private final String type;
    private final String name;
    private final String description;
    private final String filename;

    @JsonCreator
    Media(@JsonProperty(value = "id", required = false) final K id,
          @JsonProperty("type") final String type,
          @JsonProperty("name") final String name,
          @JsonProperty("description") final String description,
          @JsonProperty("filename") final String filename) {
        super(id);
        this.type = type;
        this.name = name;
        this.description = description;
        this.filename = filename;
    }

    public String getType() {
        return this.type;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getFilename() {
        return this.filename;
    }

}
