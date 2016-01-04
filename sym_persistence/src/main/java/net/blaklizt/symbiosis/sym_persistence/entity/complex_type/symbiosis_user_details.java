package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class symbiosis_user_details extends symbiosis_entity<symbiosis_user_details>
{
    private symbiosis_user user;
    private Double latitude;
    private Double longitude;

    @Basic
	@OneToOne(optional = false, cascade = ALL, fetch = LAZY)
	@JoinColumn(name = "symbiosis_user_id", referencedColumnName = "symbiosis_user_id")
	public symbiosis_user getUser() {
		return user;
	}

	public void setUser(symbiosis_user user) {
		this.user = user;
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