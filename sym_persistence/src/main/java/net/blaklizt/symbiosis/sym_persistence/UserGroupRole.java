package net.blaklizt.symbiosis.sym_persistence;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/7/13
 * Time: 9:02 PM
 */
@javax.persistence.IdClass(UserGroupRolePK.class)
@javax.persistence.Entity
@javax.persistence.Table (name = "UserGroupRole")
public class UserGroupRole implements Serializable {
	private Integer userGroupID;
	private String userGroupRoleDescription;
	private String roleID;

	@javax.persistence.Column(name = "UserGroupID")
	@javax.persistence.Id
	public Integer getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(Integer userGroupID) {
		this.userGroupID = userGroupID;
	}


	@javax.persistence.Column(name = "roleID")
	@javax.persistence.Id
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	@javax.persistence.Column(name = "userGroupRoleDescription")
	@javax.persistence.Basic
	public String getUserGroupRoleDescription() {
		return userGroupRoleDescription;
	}

	public void setUserGroupRoleDescription(String userGroupRoleDescription) {
		this.userGroupRoleDescription = userGroupRoleDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserGroupRole that = (UserGroupRole) o;

		if (roleID != null ? !roleID.equals(that.roleID) : that.roleID != null) return false;
		if (userGroupID != null ? !userGroupID.equals(that.userGroupID) : that.userGroupID != null) return false;
		if (userGroupRoleDescription != null ? !userGroupRoleDescription.equals(that.userGroupRoleDescription) : that.userGroupRoleDescription != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userGroupID != null ? userGroupID.hashCode() : 0;
		result = 31 * result + (roleID != null ? roleID.hashCode() : 0);
		result = 31 * result + (userGroupRoleDescription != null ? userGroupRoleDescription.hashCode() : 0);
		return result;
	}
}
