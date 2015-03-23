package com.bls.client.opendata;

import javax.validation.Valid;
import javax.ws.rs.client.Client;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.setup.Environment;

public class OpenDataClientConfiguration {

    @Valid
    private final JerseyClientConfiguration httpClient;
    @Valid
    @URL
    @NotEmpty
    private final String url;

    @JsonCreator
    protected OpenDataClientConfiguration(@JsonProperty("httpClient") final JerseyClientConfiguration httpClient,
            @JsonProperty("url") final String url) {
        this.httpClient = httpClient;
        this.url = url;
    }

    public Client getJerseyClientConfiguration(final Environment environment) {
        return new JerseyClientBuilder(environment) //
                .using(httpClient != null ? httpClient : new JerseyClientConfiguration()) //
                .build(url);
    }

    public String getUrl() throws ConfigurationException {
        return url;
    }
}
