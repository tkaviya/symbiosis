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
public class SymbiosisEventType {
	private Long symbiosisEventTypeID;
	private String description;

	@Id
	@Column(name = "SymbiosisEventTypeID", nullable = false, insertable = true, updatable = true)
	public Long getSymbiosisEventTypeID() {
		return symbiosisEventTypeID;
	}

	public void setSymbiosisEventTypeID(Long symbiosisEventTypeID) {
		this.symbiosisEventTypeID = symbiosisEventTypeID;
	}

	@Basic
	@Column(name = "Description", nullable = false, insertable = true, updatable = true, length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
