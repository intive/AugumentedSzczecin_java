package com.bls.rdbms.core;

import javax.persistence.*;

import com.bls.core.Identifiable;

@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(
        name = "com.bls.rdbms.core.UserJpa.findAll",
        query = "SELECT u FROM UserJpa u")})
public class UserJpa implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
