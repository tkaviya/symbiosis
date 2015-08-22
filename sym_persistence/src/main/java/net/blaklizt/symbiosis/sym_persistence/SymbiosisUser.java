package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "SymbiosisUser")
public class SymbiosisUser implements Serializable
{
	private Long symbiosisUserID;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String msisdn;
	private String password;
	private String salt;
	private Integer symbiosisUserGroupID;
	private Integer symbiosisCountryID;
	private Integer symbiosisLanguageID;
	private String authToken;
	private SymbiosisUserGroup symbiosisUserGroup;

	@Id
	@Column(name = "SymbiosisUserID", insertable = false, updatable = false)
	public Long getSymbiosisUserID() {
		return symbiosisUserID;
	}

	public void setSymbiosisUserID(Long symbiosisUserID) {
		this.symbiosisUserID = symbiosisUserID;
	}

	@Column(name = "FirstName")
	@Basic
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName")
	@Basic
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Username")
	@Basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Email")
	@Basic
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Msisdn")
	@Basic
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	@Column(name = "Password")
	@Basic
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Salt")
	@Basic
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "SymbiosisUserGroupID")
	@Basic
	public Integer getSymbiosisUserGroupID() {
		return symbiosisUserGroupID;
	}

	public void setSymbiosisUserGroupID(Integer symbiosisUserGroupID) {
		this.symbiosisUserGroupID = symbiosisUserGroupID;
	}

	@Column(name = "SymbiosisCountryID")
	@Basic
	public Integer getSymbiosisCountryID() {
		return symbiosisCountryID;
	}

	public void setSymbiosisCountryId(Integer symbiosisCountryID) {
		this.symbiosisCountryID = symbiosisCountryID;
	}

	@Column(name = "SymbiosisLanguageID")
	@Basic
	public Integer getSymbiosisLanguageID() {
		return symbiosisLanguageID;
	}

	public void setSymbiosisLanguageID(Integer symbiosisLanguageID) {
		this.symbiosisLanguageID = symbiosisLanguageID;
	}

	@Column(name = "AuthToken")
	@Basic
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@JoinTable(name="SymbiosisUserGroup")
	@JoinColumn(name="SymbiosisUserGroupID")
	@ManyToOne(targetEntity = SymbiosisUserGroup.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisUserGroup getUserGroup() {
		return symbiosisUserGroup;
	}

	public void setSymbiosisUserGroup(SymbiosisUserGroup symbiosisUserGroup) {
		this.symbiosisUserGroup = symbiosisUserGroup;
	}
}