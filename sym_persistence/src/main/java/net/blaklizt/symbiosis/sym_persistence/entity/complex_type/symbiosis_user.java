package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_group;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;

@Entity
public class symbiosis_user extends symbiosis_entity
{
	private String first_name;
	private String last_name;
	private String username;
	private String email;
	private String msisdn;
	private String password;
	private String salt;
	private Long symbiosis_user_group_id;
	private Long symbiosis_country_id;
	private Long symbiosis_language_id;
	private String auth_token;
//	private symbiosis_group user_group;

	@Column(name = "first_name")
	@Basic
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Column(name = "last_name")
	@Basic
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Column(name = "username")
	@Basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email")
	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "msisdn")
	@Basic
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Column(name = "password")
	@Basic
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt")
	@Basic
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "symbiosis_user_group_id")
	@Basic
	public Long getSymbiosis_user_group_id() {
		return symbiosis_user_group_id;
	}

	public void setSymbiosis_user_group_id(Long symbiosis_user_group_id) {
		this.symbiosis_user_group_id = symbiosis_user_group_id;
	}

	@Column(name = "symbiosis_country_id")
	@Basic
	public Long getSymbiosis_country_id() {
		return symbiosis_country_id;
	}

	public void setSymbiosis_country_id(Long symbiosis_country_id) {
		this.symbiosis_country_id = symbiosis_country_id;
	}

	@Column(name = "symbiosis_language_id")
	@Basic
	public Long getSymbiosis_language_id() {
		return symbiosis_language_id;
	}

	public void setSymbiosis_language_id(Long symbiosis_language_id) {
		this.symbiosis_language_id = symbiosis_language_id;
	}

	@Column(name = "auth_token")
	@Basic
	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

//	public void setUser_group(symbiosis_group user_group) {
//		this.user_group = user_group;
//	}
//
//    @JoinTable(name = "symbiosis_user_group")
////	@JoinColumn(name = "symbiosis_user_group_id", referencedColumnName="id")
//    @ManyToOne(targetEntity = symbiosis_group.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    public symbiosis_group getUser_group() {
//        return user_group;
//    }
}