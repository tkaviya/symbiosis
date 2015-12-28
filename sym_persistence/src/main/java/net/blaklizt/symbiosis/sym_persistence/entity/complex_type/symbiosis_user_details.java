package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class symbiosis_user_details extends symbiosis_entity
{
    private Long symbiosis_user_id;
    private Double latitude;
    private Double longitude;

    @Basic
    @Column(name = "symbiosis_user_id", nullable = false)
    public Long getSymbiosis_user_id() {
        return symbiosis_user_id;
    }

    public void setSymbiosis_user_id(Long symbiosis_user_id) {
        this.symbiosis_user_id = symbiosis_user_id;
    }

    @Basic
    @Column(nullable = true)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(nullable = true)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}