package com.augmented.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;


public class MongoConfig {

    public MongoConfig() {
    }

    @JsonProperty
    @NotEmpty
    private boolean active;

    @JsonProperty
    @NotEmpty
    private String host;

    @JsonProperty
    @NotEmpty
    private Integer port;

    @JsonProperty
    @NotEmpty
    private String name;

    public boolean isActive() {
        return active;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getName() {
        return name;
    }
}
