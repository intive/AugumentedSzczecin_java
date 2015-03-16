package com.bls.rdbms.core;

import javax.persistence.*;

import com.bls.core.Identifiable;

@Entity
@Table(name = "poi")
@NamedQueries({@NamedQuery(
        name = "com.bls.rdbms.core.PoiJpa.findAll",
        query = "SELECT p FROM PoiJpa p")})
public class PoiJpa implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
