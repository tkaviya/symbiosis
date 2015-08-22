package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
public class SymbiosisUserGroupSystemRolePK implements Serializable {
	private Integer symbiosisUserGroupId;
	private Integer symbiosisSystemId;
	private Integer symbiosisRoleId;

	@Column(name = "SymbiosisUserGroupID", nullable = false, insertable = true, updatable = true)
	@Id
	public Integer getSymbiosisUserGroupId() {
		return symbiosisUserGroupId;
	}

	public void setSymbiosisUserGroupId(Integer symbiosisUserGroupId) {
		this.symbiosisUserGroupId = symbiosisUserGroupId;
	}

	@Column(name = "SymbiosisSystemID", nullable = false, insertable = true, updatable = true)
	@Id
	public Integer getSymbiosisSystemId() {
		return symbiosisSystemId;
	}

	public void setSymbiosisSystemId(Integer symbiosisSystemId) {
		this.symbiosisSystemId = symbiosisSystemId;
	}

	@Column(name = "SymbiosisRoleID", nullable = false, insertable = true, updatable = true)
	@Id
	public Integer getSymbiosisRoleId() {
		return symbiosisRoleId;
	}

	public void setSymbiosisRoleId(Integer symbiosisRoleId) {
		this.symbiosisRoleId = symbiosisRoleId;
	}
}
