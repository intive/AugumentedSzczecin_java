package com.bls.mongodb.core;

import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UserMongodb extends MongodbMappableIdentifiableEntity<User<String>> {

    @JsonUnwrapped
    @JsonIgnoreProperties("id")
    public User<String> user;

    @Override
    public User<String> getCoreEntity() {
        return user;
    }
}
