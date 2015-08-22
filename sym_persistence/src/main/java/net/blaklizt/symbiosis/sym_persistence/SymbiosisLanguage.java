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
	private Integer symbiosisLanguageId;
	private String symbiosisLanguageName;

	@Id
	@Column(name = "SymbiosisLanguageID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisLanguageId() {
		return symbiosisLanguageId;
	}

	public void setSymbiosisLanguageId(Integer symbiosisLanguageId) {
		this.symbiosisLanguageId = symbiosisLanguageId;
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
