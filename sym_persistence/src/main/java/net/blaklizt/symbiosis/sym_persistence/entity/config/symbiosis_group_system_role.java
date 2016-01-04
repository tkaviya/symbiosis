package net.blaklizt.symbiosis.sym_persistence.entity.config;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_group;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_group_system_role extends symbiosis_enum_entity<symbiosis_group_system_role> {

	private symbiosis_group group;
	private symbiosis_system_role system_role;

	public symbiosis_group_system_role(String description, Boolean enabled, symbiosis_group group,
									   symbiosis_system_role system_role) {
		super(description, enabled);
		this.group = group;
		this.system_role = system_role;
	}

	@ManyToOne(optional = false)
    public symbiosis_group getGroup() {
        return group;
    }
    public void setGroup(symbiosis_group group) { this.group = group; }


	@ManyToOne(optional = false)
	public symbiosis_system_role getSystem_role() {
		return system_role;
	}
	public void setSystem_role(symbiosis_system_role system_role) {
		this.system_role = system_role;
	}
}
