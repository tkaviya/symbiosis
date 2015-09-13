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

	private Integer symbiosisUserGroupID;
	private Integer symbiosisSystemID;
	private Integer symbiosisRoleID;

	public SymbiosisUserGroupSystemRolePK(){}

	public SymbiosisUserGroupSystemRolePK(Integer symbiosisUserGroupID, Integer symbiosisSystemID, Integer symbiosisRoleID) {
		this.symbiosisUserGroupID = symbiosisUserGroupID;
		this.symbiosisSystemID = symbiosisSystemID;
		this.symbiosisRoleID = symbiosisRoleID;
	}

	@Column(name = "SymbiosisUserGroupID", nullable = false, insertable = true, updatable = true)
	@Id
	public Integer getSymbiosisUserGroupID() {
		return symbiosisUserGroupID;
	}

	public void setSymbiosisUserGroupID(Integer symbiosisUserGroupID) {
		this.symbiosisUserGroupID = symbiosisUserGroupID;
	}

	@Column(name = "SymbiosisSystemID", nullable = false, insertable = true, updatable = true)
	@Id
	public Integer getSymbiosisSystemID() {
		return symbiosisSystemID;
	}

	public void setSymbiosisSystemID(Integer symbiosisSystemID) {
		this.symbiosisSystemID = symbiosisSystemID;
	}

	@Column(name = "SymbiosisRoleID", nullable = false, insertable = true, updatable = true)
	@Id
	public Integer getSymbiosisRoleID() {
		return symbiosisRoleID;
	}

	public void setSymbiosisRoleID(Integer symbiosisRoleID) {
		this.symbiosisRoleID = symbiosisRoleID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SymbiosisUserGroupSystemRolePK)) return false;

		SymbiosisUserGroupSystemRolePK sugsr = (SymbiosisUserGroupSystemRolePK) o;

		if (getSymbiosisUserGroupID() != null ? !getSymbiosisUserGroupID().equals(sugsr.getSymbiosisUserGroupID()) :
				sugsr.getSymbiosisUserGroupID() != null)
			return false;

		if (getSymbiosisSystemID() != null ? !getSymbiosisSystemID().equals(sugsr.getSymbiosisSystemID()) :
				sugsr.getSymbiosisSystemID() != null)
			return false;

		return (getSymbiosisRoleID() != null ? !getSymbiosisRoleID().equals(sugsr.getSymbiosisRoleID()) :
				sugsr.getSymbiosisRoleID() != null);
	}

	@Override
	public int hashCode() {
		int result = getSymbiosisUserGroupID()			!= null ? getSymbiosisUserGroupID().hashCode() : 0;
		result = 31 * result + (getSymbiosisSystemID()	!= null ? getSymbiosisSystemID().hashCode() : 0);
		result = 31 * result + (getSymbiosisRoleID()	!= null ? getSymbiosisRoleID().hashCode() : 0);
		return result;
	}
}
