package net.blaklizt.symbiosis.sym_persistence.config;

import net.blaklizt.symbiosis.sym_persistence.helper.symbiosis_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@Table(name = "symbiosis_country")
public class symbiosis_country extends symbiosis_entity {
	private String country_name;
	private Integer country_code_prefix;

	@Basic
	@Column(name = "country_name", nullable = false, length = 50)
	public String get_country_name() {
		return country_name;
	}

	public void set_country_name(String country_name) {
		this.country_name = country_name;
	}

	@Basic
	@Column(name = "country_code_prefix", nullable = false)
	public Integer get_country_code_prefix() {
		return country_code_prefix;
	}

	public void set_country_code_prefix(Integer country_code_prefix) {
		this.country_code_prefix = country_code_prefix;
	}
}
