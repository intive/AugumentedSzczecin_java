package com.bls.mongodb.core;

import com.bls.core.IdentifiableEntity;
import com.bls.core.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Created by Marcin Podlodowski on 3/14/15.
 */
public class UserMongodb extends MongodbMappableIdentifiableEntity {
    
    @JsonUnwrapped
    @JsonIgnoreProperties("id")
    public User<String> user;
    
    @Override
    public IdentifiableEntity getCoreEntity() {
        return user;
    }
}
