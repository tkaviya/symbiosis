package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class symbiosis_user extends symbiosis_entity
{
	private String first_name;
	private String last_name;
	private String username;
	private String email;
	private String msisdn;
	private Long symbiosis_user_group_id;
	private Long symbiosis_country_id;
	private Long symbiosis_language_id;
    private Long symbiosis_user_status_id;
    private symbiosis_user_status user_status;

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

    @Column(name = "symbiosis_user_status_id")
    @Basic
    public Long getSymbiosis_user_status_id() {
        return symbiosis_user_status_id;
    }

    public void setSymbiosis_user_status_id(Long symbiosis_user_status_id) {
        this.symbiosis_user_status_id = symbiosis_user_status_id;
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