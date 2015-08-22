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
	private String username;
	private String password;
	private String salt;
	private Integer userGroupID;
	private Integer channelID;
	private String deviceID;
	private String accessSystemID;
	private String authToken;
	private Integer userStatusID;
	private Date registrationDate;
	private Date lastAuthDate;
	private Date lastLoginDate;
	private SymbiosisUserGroup symbiosisUserGroup;
	private SymbiosisChannel symbiosisChannel;
	private SymbiosisUserStatus symbiosisUserStatus;
	private Long symbiosisUserId;
	private String firstName;
	private String lastName;
	private String email;
	private String msisdn;
	private String symbiosisUserGroupId;
	private String symbiosisChannelId;
	private Integer symbiosisUserStatusId;
	private String deviceId;
	private String accessSystemId;
	private Integer symbiosisCountryId;
	private Integer symbiosisLanguageId;

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setLastAuthDate(Timestamp lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Id
	@Column(name = "SymbiosisUserID", insertable = false, updatable = false)
	public Long getSymbiosisUserID() {
		return symbiosisUserID;
	}

	public void setSymbiosisUserID(Long symbiosisUserID) {
		this.symbiosisUserID = symbiosisUserID;
	}

	@Column(name = "Username")
	@Basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Column(name = "UserGroupID")
	@Basic
	public Integer getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(Integer userGroupID) {
		this.userGroupID = userGroupID;
	}

	@Column(name = "ChannelID")
	@Basic
	public Integer getChannelID() {
		return channelID;
	}

	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
	}

	@Column(name = "DeviceID")
	@Basic
	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@Column(name = "AccessSystemID")
	@Basic
	public String getAccessSystemID() {
		return accessSystemID;
	}

	public void setAccessSystemID(String accessSystemID) {
		this.accessSystemID = accessSystemID;
	}

	@Column(name = "AuthToken")
	@Basic
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Column(name = "UserStatusID")
	@Basic
	public Integer getUserStatusID() {
		return userStatusID;
	}

	public void setUserStatusID(Integer userStatusID) {
		this.userStatusID = userStatusID;
	}

	@Column(name = "RegistrationDate")
	@Basic
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(name = "LastAuthDate")
	@Basic
	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}

	@Column(name = "LastLoginDate")
	@Basic
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@JoinTable(name="SymbiosisUserStatus")
	@JoinColumn(name="SymbiosisUserStatusID")
	@ManyToOne(targetEntity = SymbiosisUserStatus.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisUserStatus getUserStatus() {
		return symbiosisUserStatus;
	}

	public void setUserStatus(SymbiosisUserStatus symbiosisUserStatus) {
		this.symbiosisUserStatus = symbiosisUserStatus;
	}

	@JoinTable(name="SymbiosisUserGroup")
	@JoinColumn(name="SymbiosisUserGroupID")
	@ManyToOne(targetEntity = SymbiosisUserGroup.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisUserGroup getUserGroup() {
		return symbiosisUserGroup;
	}

	public void setUserGroup(SymbiosisUserGroup userGroup) {
		this.symbiosisUserGroup = symbiosisUserGroup;
	}

	@JoinTable(name="SymbiosisChannel")
	@JoinColumn(name="SymbiosisChannelID")
	@ManyToOne(targetEntity = SymbiosisChannel.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisChannel getChannel() {
		return symbiosisChannel;
	}

	public void setChannel(SymbiosisChannel symbiosisChannel) {
		this.symbiosisChannel = symbiosisChannel;
	}

	public Long getSymbiosisUserId() {
		return symbiosisUserId;
	}

	public void setSymbiosisUserId(Long symbiosisUserId) {
		this.symbiosisUserId = symbiosisUserId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getSymbiosisUserGroupId() {
		return symbiosisUserGroupId;
	}

	public void setSymbiosisUserGroupId(String symbiosisUserGroupId) {
		this.symbiosisUserGroupId = symbiosisUserGroupId;
	}

	public String getSymbiosisChannelId() {
		return symbiosisChannelId;
	}

	public void setSymbiosisChannelId(String symbiosisChannelId) {
		this.symbiosisChannelId = symbiosisChannelId;
	}

	public Integer getSymbiosisUserStatusId() {
		return symbiosisUserStatusId;
	}

	public void setSymbiosisUserStatusId(Integer symbiosisUserStatusId) {
		this.symbiosisUserStatusId = symbiosisUserStatusId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAccessSystemId() {
		return accessSystemId;
	}

	public void setAccessSystemId(String accessSystemId) {
		this.accessSystemId = accessSystemId;
	}

	public Integer getSymbiosisCountryId() {
		return symbiosisCountryId;
	}

	public void setSymbiosisCountryId(Integer symbiosisCountryId) {
		this.symbiosisCountryId = symbiosisCountryId;
	}

	public Integer getSymbiosisLanguageId() {
		return symbiosisLanguageId;
	}

	public void setSymbiosisLanguageId(Integer symbiosisLanguageId) {
		this.symbiosisLanguageId = symbiosisLanguageId;
	}
}