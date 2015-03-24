package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/29/13
 * Time: 12:21 AM
 */
@Entity
@Table (name = "UserAttribute")
public class UserAttribute {
	private Long userID;

	@Column(name = "UserID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	@Id
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userId) {
		this.userID = userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAttribute that = (UserAttribute) o;

		if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = userID != null ? userID.hashCode() : 0;
		return result;
	}
}
