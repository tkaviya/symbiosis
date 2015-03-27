package net.blaklizt.symbiosis.sym_persistence;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/7/13
 * Time: 9:02 PM
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "UserStatus")
public class UserStatus implements Serializable{
	private Integer userStatusID;
	private String description;

	@javax.persistence.Column(name = "UserStatusID")
	@javax.persistence.Id
	public Integer getUserStatusID() {
		return userStatusID;
	}

	public void setUserStatusID(Integer userStatusID) {
		this.userStatusID = userStatusID;
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

		UserStatus userStatus = (UserStatus) o;

		if (userStatusID != null ? !userStatusID.equals(userStatus.userStatusID) : userStatus.userStatusID != null)
			return false;
		else if (description != null ? !description.equals(userStatus.description) : userStatus.description != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userStatusID != null ? userStatusID.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
