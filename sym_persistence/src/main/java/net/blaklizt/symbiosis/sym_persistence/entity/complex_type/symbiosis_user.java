package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.config.symbiosis_country;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_group;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_language;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class symbiosis_user extends symbiosis_entity
{
	@Basic(fetch = LAZY, optional = true)		private String first_name;
	@Basic(fetch = LAZY, optional = true)		private String last_name;
	@Basic(fetch = LAZY, optional = true)		private String username;
	@Basic(fetch = LAZY, optional = true)		private String email;
	@Basic(fetch = LAZY, optional = true)		private String msisdn;
	@ManyToOne(fetch = LAZY, optional = false)	private symbiosis_user_status user_status;
	@ManyToOne(fetch = LAZY, optional = false)	private symbiosis_country country;
	@ManyToOne(fetch = LAZY, optional = false)	private symbiosis_language language;
	@ManyToOne(fetch = LAZY, optional = false)	private symbiosis_group user_group;
//	private Long sym_user_group_id;
//	private Long sym_country_id;
//	private Long sym_language_id;
//    private Long sym_user_status_id;

	@Column(name = "first_name")
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Column(name = "last_name")
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "msisdn")
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

//	@Column(name = "sym_user_group_id")
//
//	public Long getSym_user_group_id() {
//		return sym_user_group_id;
//	}
//
//	public void setSym_user_group_id(Long sym_user_group_id) {
//		this.sym_user_group_id = sym_user_group_id;
//	}
//
//	@Column(name = "sym_country_id")
//
//	public Long getSym_country_id() {
//		return sym_country_id;
//	}
//
//	public void setSym_country_id(Long sym_country_id) {
//		this.sym_country_id = sym_country_id;
//	}
//
//	@Column(name = "sym_language_id")
//
//	public Long getSym_language_id() {
//		return sym_language_id;
//	}
//
//	public void setSym_language_id(Long sym_language_id) {
//		this.sym_language_id = sym_language_id;
//	}
//
//    @Column(name = "sym_user_status_id")
//    @Basic
//    public Long getSym_user_status_id() {
//        return sym_user_status_id;
//    }
//
//    public void setSym_user_status_id(Long sym_user_status_id) {
//        this.sym_user_status_id = sym_user_status_id;
//    }

	public void setUser_group(symbiosis_group user_group) {
		this.user_group = user_group;
	}

//    @JoinTable(name = "symbiosis_user_group", )
//	@JoinColumn(name = "sym_user_group_id", referencedColumnName="id")
    public symbiosis_group getUser_group() {
        return user_group;
    }
}