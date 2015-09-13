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
public class SymbiosisOption {
	private Long symbiosisOptionID;
	private String description;

	@Id
	@Column(name = "SymbiosisOptionID", nullable = false, insertable = true, updatable = true)
	public Long getSymbiosisOptionID() {
		return symbiosisOptionID;
	}

	public void setSymbiosisOptionID(Long symbiosisOptionID) {
		this.symbiosisOptionID = symbiosisOptionID;
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
