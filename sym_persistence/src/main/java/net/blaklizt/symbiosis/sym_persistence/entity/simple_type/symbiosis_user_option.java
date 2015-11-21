package net.blaklizt.symbiosis.sym_persistence.entity.simple_type;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@Table(name ="symbiosis_user_option")
public class symbiosis_user_option extends symbiosis_entity {
	private Long symbiosis_user_id;
	private Long symbiosis_option_id;
	private String option_value;

	@Basic
	@Column(name = "symbiosis_user_id", nullable = false)
	public Long getSymbiosis_user_id() {
		return symbiosis_user_id;
	}

	public void setSymbiosis_user_id(Long symbiosis_user_id) {
		this.symbiosis_user_id = symbiosis_user_id;
	}

	@Basic
	@Column(name = "symbiosis_option_id", nullable = false)
	public Long getSymbiosis_option_id() {
		return symbiosis_option_id;
	}

	public void setSymbiosis_option_id(Long symbiosis_option_id) {
		this.symbiosis_option_id = symbiosis_option_id;
	}

	@Basic
	@Column(name = "option_value", nullable = false, length = 256)
	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}
}
