package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@IdClass(SymbiosisUserGroupSystemRolePK.class)
public class SymbiosisUserGroupSystemRole {
	private Integer symbiosisUserGroupID;
	private Integer symbiosisSystemID;
	private Integer symbiosisRoleID;
	private String symbiosisUserGroupSystemRoleDescription;

	@Id
	@Column(name = "SymbiosisUserGroupID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisUserGroupID() {
		return symbiosisUserGroupID;
	}

	public void setSymbiosisUserGroupID(Integer symbiosisUserGroupId) {
		this.symbiosisUserGroupID = symbiosisUserGroupId;
	}

	@Id
	@Column(name = "SymbiosisSystemID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisSystemID() {
		return symbiosisSystemID;
	}

	public void setSymbiosisSystemID(Integer symbiosisSystemId) {
		this.symbiosisSystemID = symbiosisSystemId;
	}

	@Id
	@Column(name = "SymbiosisRoleID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisRoleID() {
		return symbiosisRoleID;
	}

	public void setSymbiosisRoleID(Integer symbiosisRoleId) {
		this.symbiosisRoleID = symbiosisRoleId;
	}

	@Basic
	@Column(name = "SymbiosisUserGroupSystemRoleDescription", nullable = false, insertable = true, updatable = true)
	public String getSymbiosisUserGroupSystemRoleDescription() {
		return symbiosisUserGroupSystemRoleDescription;
	}

	public void setSymbiosisUserGroupSystemRoleDescription(String symbiosisUserGroupSystemRoleDescription) {
		this.symbiosisUserGroupSystemRoleDescription = symbiosisUserGroupSystemRoleDescription;
	}
}
