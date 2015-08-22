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
	private Integer symbiosisUserGroupId;
	private Integer symbiosisSystemId;
	private Integer symbiosisRoleId;
	private String symbiosisUserGroupSystemRoleDescription;

	@Id
	@Column(name = "SymbiosisUserGroupID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisUserGroupId() {
		return symbiosisUserGroupId;
	}

	public void setSymbiosisUserGroupId(Integer symbiosisUserGroupId) {
		this.symbiosisUserGroupId = symbiosisUserGroupId;
	}

	@Id
	@Column(name = "SymbiosisSystemID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisSystemId() {
		return symbiosisSystemId;
	}

	public void setSymbiosisSystemId(Integer symbiosisSystemId) {
		this.symbiosisSystemId = symbiosisSystemId;
	}

	@Id
	@Column(name = "SymbiosisRoleID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisRoleId() {
		return symbiosisRoleId;
	}

	public void setSymbiosisRoleId(Integer symbiosisRoleId) {
		this.symbiosisRoleId = symbiosisRoleId;
	}

	@Basic
	@Column(name = "SymbiosisUserGroupSystemRoleDescription", nullable = false, insertable = true, updatable = true, length = 20)
	public String getSymbiosisUserGroupSystemRoleDescription() {
		return symbiosisUserGroupSystemRoleDescription;
	}

	public void setSymbiosisUserGroupSystemRoleDescription(String symbiosisUserGroupSystemRoleDescription) {
		this.symbiosisUserGroupSystemRoleDescription = symbiosisUserGroupSystemRoleDescription;
	}
}
