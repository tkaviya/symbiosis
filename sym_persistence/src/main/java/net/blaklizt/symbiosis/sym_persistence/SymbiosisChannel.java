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
public class SymbiosisChannel {
	private Integer symbiosisChannelID;
	private String description;

	@Id
	@Column(name = "SymbiosisChannelID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisChannelID() {
		return symbiosisChannelID;
	}

	public void setSymbiosisChannelID(Integer symbiosisChannelID) {
		this.symbiosisChannelID = symbiosisChannelID;
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
