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
public class SymbiosisLanguage {
	private Integer symbiosisLanguageID;
	private String symbiosisLanguageName;

	@Id
	@Column(name = "SymbiosisLanguageID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisLanguageID() {
		return symbiosisLanguageID;
	}

	public void setSymbiosisLanguageID(Integer symbiosisLanguageID) {
		this.symbiosisLanguageID = symbiosisLanguageID;
	}

	@Basic
	@Column(name = "SymbiosisLanguageName", nullable = false, insertable = true, updatable = true, length = 50)
	public String getSymbiosisLanguageName() {
		return symbiosisLanguageName;
	}

	public void setSymbiosisLanguageName(String symbiosisLanguageName) {
		this.symbiosisLanguageName = symbiosisLanguageName;
	}
}
