package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "SymbiosisUser")
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
	private UserAttribute userAttribute;
	private UserGroup userGroup;
	private Channel channel;
	private UserStatus userStatus;

	@javax.persistence.Id
	@javax.persistence.Column(name = "SymbiosisUserID", insertable = false, updatable = false)
	@javax.persistence.GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long getSymbiosisUserID() {
		return symbiosisUserID;
	}

	public void setSymbiosisUserID(Long symbiosisUserID) {
		this.symbiosisUserID = symbiosisUserID;
	}

	@javax.persistence.Column(name = "Username")
	@javax.persistence.Basic
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@javax.persistence.Column(name = "Password")
	@javax.persistence.Basic
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@javax.persistence.Column(name = "Salt")
	@javax.persistence.Basic
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@javax.persistence.Column(name = "UserGroupID")
	@javax.persistence.Basic
	public Integer getUserGroupID() {
		return userGroupID;
	}

	public void setUserGroupID(Integer userGroupID) {
		this.userGroupID = userGroupID;
	}

	@javax.persistence.Column(name = "ChannelID")
	@javax.persistence.Basic
	public Integer getChannelID() {
		return channelID;
	}

	public void setChannelID(Integer channelID) {
		this.channelID = channelID;
	}

	@javax.persistence.Column(name = "DeviceID")
	@javax.persistence.Basic
	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@javax.persistence.Column(name = "AccessSystemID")
	@javax.persistence.Basic
	public String getAccessSystemID() {
		return accessSystemID;
	}

	public void setAccessSystemID(String accessSystemID) {
		this.accessSystemID = accessSystemID;
	}


	@javax.persistence.Column(name = "AuthToken")
	@javax.persistence.Basic
	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@javax.persistence.Column(name = "UserStatusID")
	@javax.persistence.Basic
	public Integer getUserStatusID() {
		return userStatusID;
	}

	public void setUserStatusID(Integer userStatusID) {
		this.userStatusID = userStatusID;
	}

	@javax.persistence.Column(name = "RegistrationDate")
	@javax.persistence.Basic
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@javax.persistence.Column(name = "LastAuthDate")
	@javax.persistence.Basic
	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	public void setLastAuthDate(Date lastAuthDate) {
		this.lastAuthDate = lastAuthDate;
	}


	@javax.persistence.Column(name = "LastLoginDate")
	@javax.persistence.Basic
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@javax.persistence.JoinTable(name="UserAttribute")
	@javax.persistence.JoinColumn(name="SymbiosisUserID")
	@OneToOne(targetEntity = UserAttribute.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public UserAttribute getUserAttribute() {
		return userAttribute;
	}

	public void setUserAttribute(UserAttribute userAttribute) {
		this.userAttribute = userAttribute;
	}

	@javax.persistence.JoinTable(name="UserStatus")
	@javax.persistence.JoinColumn(name="UserStatusID")
	@ManyToOne(targetEntity = UserStatus.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@javax.persistence.JoinTable(name="UserGroup")
	@javax.persistence.JoinColumn(name="UserGroupID")
	@ManyToOne(targetEntity = UserGroup.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	@javax.persistence.JoinTable(name="Channel")
	@javax.persistence.JoinColumn(name="ChannelID")
	@ManyToOne(targetEntity = Channel.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SymbiosisUser symbiosisUser = (SymbiosisUser) o;

		if (symbiosisUserID != symbiosisUser.symbiosisUserID) return false;
		if (username != null ? !username.equals(symbiosisUser.username) : symbiosisUser.username != null) return false;
		if (password != null ? !password.equals(symbiosisUser.password) : symbiosisUser.password != null) return false;
		if (salt != null ? !salt.equals(symbiosisUser.salt) : symbiosisUser.salt != null) return false;
		if (userGroupID != null ? !userGroupID.equals(symbiosisUser.userGroupID) : symbiosisUser.userGroupID != null) return false;
		if (channelID != null ? !channelID.equals(symbiosisUser.channelID) : symbiosisUser.channelID != null) return false;
		if (deviceID != null ? !deviceID.equals(symbiosisUser.deviceID) : symbiosisUser.deviceID != null) return false;
		if (accessSystemID != null ? !accessSystemID.equals(symbiosisUser.accessSystemID) : symbiosisUser.accessSystemID != null) return false;
		if (authToken != null ? !authToken.equals(symbiosisUser.authToken) : symbiosisUser.authToken != null) return false;
		if (userStatusID != null ? !userStatusID.equals(symbiosisUser.userStatusID) : symbiosisUser.userStatusID != null) return false;
		if (lastLoginDate != null ? !lastLoginDate.equals(symbiosisUser.lastLoginDate) : symbiosisUser.lastLoginDate != null) return false;
		if (userAttribute != null ? !userAttribute.equals(symbiosisUser.userAttribute) : symbiosisUser.userAttribute != null) return false;
		if (userGroup != null ? !userGroup.equals(symbiosisUser.userGroup) : symbiosisUser.userGroup != null) return false;
		if (channel != null ? !channel.equals(symbiosisUser.channel) : symbiosisUser.channel != null) return false;
		if (userStatus != null ? !userStatus.equals(symbiosisUser.userStatus) : symbiosisUser.userStatus != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		Long result = symbiosisUserID;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (salt != null ? salt.hashCode() : 0);
		result = 31 * result + (userGroupID != null ? userGroupID.hashCode() : 0);
		result = 31 * result + (channelID != null ? channelID.hashCode() : 0);
		result = 31 * result + (deviceID != null ? deviceID.hashCode() : 0);
		result = 31 * result + (accessSystemID != null ? accessSystemID.hashCode() : 0);
		result = 31 * result + (authToken != null ? authToken.hashCode() : 0);
		result = 31 * result + (userStatusID != null ? userStatusID.hashCode() : 0);
		result = 31 * result + (lastLoginDate != null ? lastLoginDate.hashCode() : 0);
		result = 31 * result + (userAttribute != null ? userAttribute.hashCode() : 0);
		result = 31 * result + (userGroup != null ? userGroup.hashCode() : 0);
		result = 31 * result + (channel != null ? channel.hashCode() : 0);
		result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
		return result.intValue();
	}
}