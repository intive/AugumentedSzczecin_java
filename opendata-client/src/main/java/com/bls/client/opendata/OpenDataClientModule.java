package com.bls.client.opendata;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.Client;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.setup.Environment;

public class OpenDataClientModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Singleton
    @Provides
    public Client provideOpenDataClient(final Environment environment, final OpenDataClientConfiguration configuration) {
        return configuration.getJerseyClientConfiguration(environment);
    }

    @Singleton
    @Provides
    @Named("openDataUrl")
    public String provideOpenDataClient(final OpenDataClientConfiguration configuration) throws ConfigurationException {
        return configuration.getUrl();
    }
}
