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
public class SymbiosisSystem {
	private Integer symbiosisSystemId;
	private String description;

	@Id
	@Column(name = "SymbiosisSystemID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisSystemId() {
		return symbiosisSystemId;
	}

	public void setSymbiosisSystemId(Integer symbiosisSystemId) {
		this.symbiosisSystemId = symbiosisSystemId;
	}

	@Basic
	@Column(name = "Description", nullable = false, insertable = true, updatable = true, length = 128)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
