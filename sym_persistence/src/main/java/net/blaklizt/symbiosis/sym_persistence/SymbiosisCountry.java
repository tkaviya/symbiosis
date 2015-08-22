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
public class SymbiosisCountry {
	private Integer symbiosisCountryId;
	private String countryName;
	private Integer countryCodePrefix;

	@Id
	@Column(name = "SymbiosisCountryID", nullable = false, insertable = true, updatable = true)
	public Integer getSymbiosisCountryId() {
		return symbiosisCountryId;
	}

	public void setSymbiosisCountryId(Integer symbiosisCountryId) {
		this.symbiosisCountryId = symbiosisCountryId;
	}

	@Basic
	@Column(name = "CountryName", nullable = false, insertable = true, updatable = true, length = 50)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Basic
	@Column(name = "CountryCodePrefix", nullable = false, insertable = true, updatable = true)
	public Integer getCountryCodePrefix() {
		return countryCodePrefix;
	}

	public void setCountryCodePrefix(Integer countryCodePrefix) {
		this.countryCodePrefix = countryCodePrefix;
	}
}
