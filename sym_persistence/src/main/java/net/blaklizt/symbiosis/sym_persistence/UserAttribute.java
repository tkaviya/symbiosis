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

	private Long authUserID;
	private String firstName;
	private String lastName;
	private String email;
	private String msisdn;
	private Integer countryID;

	@javax.persistence.OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@javax.persistence.JoinTable(name="SymbiosisUser",
			   joinColumns = {@javax.persistence.JoinColumn(name="SymbiosisUserID", referencedColumnName="SymbiosisUserID")},
		inverseJoinColumns = {@javax.persistence.JoinColumn(name="SymbiosisUserID", referencedColumnName="SymbiosisUserID")})
	SymbiosisUser symbiosisUser;

//	@javax.persistence.ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@javax.persistence.JoinTable(name="County",
//			   joinColumns = {@javax.persistence.JoinColumn(name="CountryID", referencedColumnName="CountryID")},
//	Country country;

	@Column(name = "AuthUserID", nullable = false, insertable = true, updatable = true)
	@Id
	public Long getAuthUserID() {
		return authUserID;
	}

	public void setAuthUserID(Long authUserID) {
		this.authUserID = authUserID;
	}

	@Column(name = "FirstName", nullable = false, insertable = true, updatable = true)
	@Basic
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName", nullable = false, insertable = true, updatable = true)
	@Basic
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Email", nullable = false, insertable = true, updatable = true)
	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Msisdn", nullable = false, insertable = true, updatable = true)
	@Basic
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Column(name = "CountryID", nullable = false, insertable = true, updatable = true)
	@Basic
	public Integer getCountryID() {
		return countryID;
	}

	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}

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

		if (authUserID != null ? !authUserID.equals(that.authUserID) : that.authUserID != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = authUserID != null ? authUserID.hashCode() : 0;
		return result;
	}
}
