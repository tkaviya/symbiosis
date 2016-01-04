package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.config.symbiosis_country;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_group;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_language;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "symbiosis_user_id"))
public class symbiosis_user extends symbiosis_entity<symbiosis_user>
{
	private String first_name;
	private String last_name;
	private Date date_of_birth;
	private String username;
	private String password;
	private Integer password_tries;
	private String salt;
	private String email;
	private String msisdn;
	private symbiosis_user_status user_status;
	private symbiosis_country country;
	private symbiosis_language language;
//	private ArrayList<symbiosis_auth_user> user_channels;
	private symbiosis_user_details user_details;

	@Basic(fetch = LAZY, optional = true)
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Basic(fetch = LAZY, optional = true)
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Basic(fetch = LAZY, optional = true)
	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	@Basic(fetch = LAZY, optional = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic(fetch = LAZY, optional = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Basic(fetch = LAZY, optional = true)
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Basic(optional = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic(optional = false)
	public Integer getPassword_tries() {
		return password_tries;
	}

	public void setPassword_tries(Integer password_tries) {
		this.password_tries = password_tries;
	}

	@Basic(optional = false)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@ManyToOne(optional = false)
	public symbiosis_user_status getUser_status() {
		return user_status;
	}

	public void setUser_status(symbiosis_user_status user_status) {
		this.user_status = user_status;
	}

	@ManyToOne(optional = false, fetch = LAZY)
	public symbiosis_country getCountry() {
		return country;
	}

	public void setCountry(symbiosis_country country) {
		this.country = country;
	}

	@ManyToOne(optional = false, fetch = LAZY)
	public symbiosis_language getLanguage() {
		return language;
	}

	public void setLanguage(symbiosis_language language) {
		this.language = language;
	}

//	@JoinColumn(name = "symbiosis_user_id", referencedColumnName = "symbiosis_user_id")
//	@OneToMany(mappedBy = "symbiosis_user_id", targetEntity = symbiosis_auth_user.class, fetch = LAZY, cascade = ALL)
//	public ArrayList<symbiosis_auth_user> getUser_channels() {
//		if (user_channels == null) {
//			user_channels = new ArrayList<>();
//		}
//		return user_channels;
//	}
//
//	public void setUser_channels(ArrayList<symbiosis_auth_user> user_channels) {
//		this.user_channels = user_channels;
//	}

	@OneToOne(optional = false, cascade = ALL, fetch = LAZY)
	@JoinColumn(name = "symbiosis_user_id", referencedColumnName = "symbiosis_user_id")
	public symbiosis_user_details getUser_details() {
		return user_details;
	}

	public void setUser_details(symbiosis_user_details user_details) {
		this.user_details = user_details;
	}
}