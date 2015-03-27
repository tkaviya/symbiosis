package net.blaklizt.symbiosis.sym_persistence;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/7/13
 * Time: 9:02 PM
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "UserGroup")
public class UserGroup implements Serializable{
	private Integer userGroupID;
	private String description;

	@javax.persistence.Column(name = "UserGroupID")
	@javax.persistence.Id
	public Integer getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(Integer userGroupID) {
		this.userGroupID = userGroupID;
	}

	@javax.persistence.Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserGroup userGroup = (UserGroup) o;

		if (userGroupID != null ? !userGroupID.equals(userGroup.userGroupID) : userGroup.userGroupID != null)
			return false;
		else if (description != null ? !description.equals(userGroup.description) : userGroup.description != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userGroupID != null ? userGroupID.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
