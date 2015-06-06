package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 8/29/13
 * Time: 12:21 AM
 */
@Entity
@Table (name = "UserAttribute")
public class UserAttribute {

	private Long symbiosisUserID;
	private String firstName;
	private String lastName;
	private String email;
	private String msisdn;
	private Integer countryID;
	private SymbiosisUser symbiosisUser;

//	@javax.persistence.ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@javax.persistence.JoinTable(name="County",
//			   joinColumns = {@javax.persistence.JoinColumn(name="CountryID", referencedColumnName="CountryID")},
//	private Country country;

	@javax.persistence.Id
	@javax.persistence.Column(name = "SymbiosisUserID", insertable = true, updatable = true)
	public Long getSymbiosisUserID() {
		return symbiosisUserID;
	}

	public void setSymbiosisUserID(Long symbiosisUserID) {
		this.symbiosisUserID = symbiosisUserID;
	}

	@javax.persistence.Column(name = "FirstName", nullable = false, insertable = true, updatable = true)
	@javax.persistence.Basic
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@javax.persistence.Column(name = "LastName", nullable = false, insertable = true, updatable = true)
	@javax.persistence.Basic
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@javax.persistence.Column(name = "Email", nullable = false, insertable = true, updatable = true)
	@javax.persistence.Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@javax.persistence.Column(name = "Msisdn", nullable = false, insertable = true, updatable = true)
	@javax.persistence.Basic
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@javax.persistence.Column(name = "CountryID", nullable = false, insertable = true, updatable = true)
	@javax.persistence.Basic
	public Integer getCountryID() {
		return countryID;
	}

	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}

	@javax.persistence.JoinTable(name="SymbiosisUser")
	@javax.persistence.JoinColumn(name="SymbiosisUserID")
	@OneToOne(targetEntity = SymbiosisUser.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisUser getSymbiosisUser() {
		return symbiosisUser;
	}

	public void setSymbiosisUser(SymbiosisUser symbiosisUser) {
		this.symbiosisUser = symbiosisUser;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAttribute that = (UserAttribute) o;

		if (symbiosisUserID != null ? !symbiosisUserID.equals(that.symbiosisUserID) : that.symbiosisUserID != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = symbiosisUserID != null ? symbiosisUserID.hashCode() : 0;
		return result;
	}
}
