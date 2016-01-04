package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_option;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_user_option extends symbiosis_entity<symbiosis_user_option> {
	private symbiosis_user user;
	private symbiosis_option option;
	private String option_value;

	@JoinColumn(name = "symbiosis_user_id", referencedColumnName = "symbiosis_user_id")
	@ManyToOne(optional = false)
	public symbiosis_user getUser() {
		return user;
	}

	public void setUser(symbiosis_user user) {
		this.user = user;
	}

	@ManyToOne(optional = false)
	public symbiosis_option getOption() {
		return option;
	}

	public void setOption(symbiosis_option option) {
		this.option = option;
	}

	@Column(nullable = false, length = 256)
	public String getOption_value() {
		return option_value;
	}

	public void setOption_value(String option_value) {
		this.option_value = option_value;
	}
}
