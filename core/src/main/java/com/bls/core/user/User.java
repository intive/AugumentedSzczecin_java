package com.bls.core.user;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Marcin Podlodowski on 3/10/15.
 */
public class User<K> extends IdentifiableEntity<K> {

    @NotEmpty
    private String email;
    @NotNull
    @Size(min = 2, max = 64)
    private String password;

    @JsonCreator
    public User(@JsonProperty(value = "id", required = false) final K id, 
                @JsonProperty("email") final String email, 
                @JsonProperty("password") final String password) {
        super(id);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

}
