package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class SymbiosisRole {
	private Integer symbiosisRoleID;
	private String description;
	private Byte enabled;

	@Id
	@Column(name = "SymbiosisRoleID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisRoleID() {
		return symbiosisRoleID;
	}

	public void setSymbiosisRoleID(Integer symbiosisRoleId) {
		this.symbiosisRoleID = symbiosisRoleId;
	}

	@Basic
	@Column(name = "Description", nullable = true, insertable = true, updatable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "Enabled", nullable = false, insertable = true, updatable = true)
	public Byte getEnabled() {
		return enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}
}
