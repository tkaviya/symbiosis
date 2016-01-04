package net.blaklizt.symbiosis.sym_persistence.entity.config;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_role;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_system_role extends symbiosis_enum_entity<symbiosis_system_role> {

	private symbiosis_system system;
	private symbiosis_role role;

	public symbiosis_system_role(String description, Boolean enabled,
		symbiosis_system system, symbiosis_role role) {
		super(description, enabled);
		this.system = system;
		this.role = role;
	}

	@ManyToOne(optional = false)
    public symbiosis_system getSystem() {
        return system;
    }

    public void setSystem(symbiosis_system system) {
        this.system = system;
    }

	@ManyToOne(optional = false)
	public symbiosis_role getRole() {
		return role;
	}

	public void setRole(symbiosis_role role) {
		this.role = role;
	}
}
