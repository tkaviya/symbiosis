package net.blaklizt.symbiosis.sym_persistence.entity.config;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_group;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_role;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_system_role {

    private symbiosis_group group;
	private symbiosis_system system;
	private symbiosis_role role;
	private boolean enabled;

	public symbiosis_system_role(symbiosis_group group, symbiosis_system system, symbiosis_role role, boolean enabled) {
		this.group = group;
		this.system = system;
		this.role = role;
		this.enabled = enabled;
	}

	@Column(nullable = false, updatable = false)
    public symbiosis_group getGroup() {
        return group;
    }

    public void setGroup(symbiosis_group group) { this.group = group; }

	@Column(nullable = false, updatable = false)
    public symbiosis_system getSystem() {
        return system;
    }

    public void setSystem(symbiosis_system system) {
        this.system = system;
    }

	@Column(nullable = false, updatable = false)
	public symbiosis_role getRole() {
		return role;
	}

	public void setRole(symbiosis_role role) {
		this.role = role;
	}

	@Column(nullable = false, updatable = false)
	public Boolean isEnabled() { return enabled; }

	/* this function is private because we do not want to be
	 * able to update an enum entity to set enabled flag programmatically */
	private void setEnabled(Boolean enabled) { this.enabled = enabled; }

}
