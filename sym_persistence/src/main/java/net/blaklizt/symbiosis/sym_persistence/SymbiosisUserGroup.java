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
public class SymbiosisUserGroup {
	private Integer symbiosisUserGroupId;
	private String description;

	@Id
	@Column(name = "SymbiosisUserGroupID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisUserGroupId() {
		return symbiosisUserGroupId;
	}

	public void setSymbiosisUserGroupId(Integer symbiosisUserGroupId) {
		this.symbiosisUserGroupId = symbiosisUserGroupId;
	}

	@Basic
	@Column(name = "Description", nullable = true, insertable = true, updatable = true, length = 20)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
