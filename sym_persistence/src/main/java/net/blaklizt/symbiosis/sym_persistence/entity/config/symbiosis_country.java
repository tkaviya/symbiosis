package net.blaklizt.symbiosis.sym_persistence.entity.config;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_country extends symbiosis_enum_entity<symbiosis_country> {

	private String country_name;
	private String country_code_prefix;

	public symbiosis_country(String description, Boolean enabled, String country_name, String country_code_prefix) {
		super(description, enabled);
		this.country_name = country_name;
		this.country_code_prefix = country_code_prefix;
	}

	@Basic
	@Column(name = "country_name", nullable = false, length = 50)
	public String get_country_name() {
		return country_name;
	}

	public void set_country_name(String country_name) {
		this.country_name = country_name;
	}

	@Basic
	@Column(name = "country_code_prefix", nullable = false, length = 5)
	public String get_country_code_prefix() {
		return country_code_prefix;
	}

	public void set_country_code_prefix(String country_code_prefix) {
		this.country_code_prefix = country_code_prefix;
	}
}
