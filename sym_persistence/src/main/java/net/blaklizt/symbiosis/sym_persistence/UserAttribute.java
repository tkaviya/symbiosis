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


	@javax.persistence.OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@javax.persistence.JoinTable(name="SymbiosisUser",
			   joinColumns = {@javax.persistence.JoinColumn(name="SymbiosisUserID", referencedColumnName="SymbiosisUserID")},
		inverseJoinColumns = {@javax.persistence.JoinColumn(name="SymbiosisUserID", referencedColumnName="SymbiosisUserID")})
	SymbiosisUser symbiosisUser;

	@Column(name = "AuthUserID", nullable = false, insertable = true, updatable = true)
	@Id
	public Long getAuthUserID() {
		return authUserID;
	}

	public void setAuthUserID(Long authUserID) {
		this.authUserID = authUserID;
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
