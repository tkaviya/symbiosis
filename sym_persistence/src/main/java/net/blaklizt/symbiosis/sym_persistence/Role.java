package net.blaklizt.symbiosis.sym_persistence;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/7/13
 * Time: 9:01 PM
 */
@javax.persistence.Entity
@javax.persistence.Table (name = "Role")
public class Role implements Serializable
{
	protected Integer roleID;
	private String description;
	protected boolean enabled;

	@javax.persistence.Column(name = "RoleID")
	@javax.persistence.Id
	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	@javax.persistence.Column(name = "Description")
	@javax.persistence.Basic
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@javax.persistence.Column(name = "Enabled")
	@javax.persistence.Basic
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (enabled != role.enabled) return false;
		if (description != null ? !description.equals(role.description) : role.description != null) return false;
		if (roleID != null ? !roleID.equals(role.roleID) : role.roleID != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = roleID != null ? roleID.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (enabled ? 1 : 0);
		return result;
	}
}
