package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class symbiosis_auth_user extends symbiosis_entity<symbiosis_auth_user>
{
	private symbiosis_user user;
	private symbiosis_system access_system;
	private symbiosis_channel channel;
	private String device_id;
	private String auth_token;
	private Date registration_date;
	private Date last_auth_date;
	private Date last_login_date;

	@JoinColumn(name = "symbiosis_user_id", referencedColumnName = "symbiosis_user_id")
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	public symbiosis_user getUser() {
		return user;
	}

	public void setUser(symbiosis_user user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public symbiosis_channel getChannel() {
		return channel;
	}

	public void setChannel(symbiosis_channel channel) {
		this.channel = channel;
	}

    @Column(name = "device_id")
    @Basic
    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

	@ManyToOne(optional = false)
    public symbiosis_system getAccess_system() {
        return access_system;
    }

    public void setAccess_system(symbiosis_system access_system) {
        this.access_system = access_system;
    }

    @Column(name = "auth_token")
    @Basic
    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    @Column(name = "registration_date")
    @Basic
    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    @Column(name = "last_auth_date")
    @Basic
    public Date getLast_auth_date() {
        return last_auth_date;
    }

    public void setLast_auth_date(Date last_auth_date) {
        this.last_auth_date = last_auth_date;
    }

    @Column(name = "last_login_date")
    @Basic
    public Date getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
    }
}