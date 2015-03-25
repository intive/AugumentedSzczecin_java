package com.bls.rdbms.core;

import com.bls.core.Identifiable;
import com.bls.core.poi.Tag;

import javax.persistence.*;

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
    @Column(name = "tag", nullable = false)
    private Tag tag;
    @Column(name = "longitude", nullable = false)
    private Long longitude;
    @Column(name = "latitude", nullable = false)
    private Long latitude;

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

    public Tag getTag() {
        return tag;
    }

    public void setTag(final Tag tag) {
        this.tag = tag;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(final Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(final Long latitude) {
        this.latitude = latitude;
    }
}
