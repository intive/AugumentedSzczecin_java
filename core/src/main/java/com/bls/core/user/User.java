package com.bls.core.user;

import javax.validation.Constraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.bls.core.IdentifiableEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User of the backend. Can be authenticated by password.
 * <p>
 * Note password is never stored by plaintext.
 * </p>
 *
 * @param <K> Key type for this entity
 */
@JsonInclude(Include.NON_EMPTY)
@UniqueUser
public class User<K> extends IdentifiableEntity<K> {

    @NotEmpty
    @Email
    private final String email;
    @NotEmpty
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public User<K> createUserWithHashedPassword(final String hashedPassword) {
        return new User<>(getId(), email, hashedPassword);
    }
}
