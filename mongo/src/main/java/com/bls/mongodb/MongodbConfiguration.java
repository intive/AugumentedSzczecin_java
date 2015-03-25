package com.bls.mongodb;

import java.net.UnknownHostException;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import io.dropwizard.validation.PortRange;

public class MongodbConfiguration {

    @NotEmpty
    private final String host;
    @PortRange
    private final Integer port;
    @NotEmpty
    private final String dbname;

    @JsonCreator
    public MongodbConfiguration(@JsonProperty("host") final String host,
            @JsonProperty("port") final Integer port,
            @JsonProperty("dbname") final String dbname) {
        this.host = host;
        this.port = port;
        this.dbname = dbname;
    }

    public String getDbname() {
        return dbname;
    }

    public MongoClient buildMongoClient() throws UnknownHostException {
        final ServerAddress serverAddress = new ServerAddress(host, port);
        return new MongoClient(serverAddress);
    }
}
