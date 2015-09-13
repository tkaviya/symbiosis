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
public class SymbiosisUserOption {
	private Long symbiosisUserID;
	private Long symbiosisOptionID;
	private String optionValue;

	@Id
	@Column(name = "SymbiosisUserID", nullable = false, insertable = true, updatable = true)
	public Long getSymbiosisUserID() {
		return symbiosisUserID;
	}

	public void setSymbiosisUserID(Long symbiosisUserID) {
		this.symbiosisUserID = symbiosisUserID;
	}

	@Basic
	@Column(name = "SymbiosisOptionID", nullable = true, insertable = true, updatable = true)
	public Long getSymbiosisOptionID() {
		return symbiosisOptionID;
	}

	public void setSymbiosisOptionID(Long symbiosisOptionID) {
		this.symbiosisOptionID = symbiosisOptionID;
	}

	@Basic
	@Column(name = "OptionValue", nullable = false, insertable = true, updatable = true, length = 256)
	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
}
