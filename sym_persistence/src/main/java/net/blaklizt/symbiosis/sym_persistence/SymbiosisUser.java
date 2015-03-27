package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

;

@javax.persistence.Entity
@javax.persistence.Table(name = "SymbiosisUser")
public class SymbiosisUser implements Serializable
{
	private enum UserStatus
	{
		INACTIVE(0),
		ACTIVE(1),
		PENDING(2),
		SUSPENDED(3),
		BLOCKED(4),
		UNKNOWN(10);

		private int value;

		UserStatus(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}

		public static UserStatus fromValue(int status)
		{
			for (UserStatus userStatus : UserStatus.values())
			{
				if (userStatus.getValue() == status)
					return userStatus;
			}
			return UNKNOWN;
		}
	}

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
	private Date lastLoginDate;

	@javax.persistence.OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@javax.persistence.JoinTable(name="UserAttribute",
			joinColumns = {@javax.persistence.JoinColumn(name="SymbiosisUserID", referencedColumnName="SymbiosisUserID")},
	 inverseJoinColumns = {@javax.persistence.JoinColumn(name="SymbiosisUserID", referencedColumnName="SymbiosisUserID")})
	private UserAttribute userAttribute;

	@javax.persistence.ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@javax.persistence.JoinTable(name="UserGroup", joinColumns = {@javax.persistence.JoinColumn(name="UserGroupID", referencedColumnName="UserGroupID")})
	private UserGroup userGroup;

	@javax.persistence.ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@javax.persistence.JoinTable(name="Channel", joinColumns = {@javax.persistence.JoinColumn(name="ChannelID", referencedColumnName="ChannelID")})
	private Channel channel;

	@javax.persistence.ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@javax.persistence.JoinTable(name="UserStatus", joinColumns = {@javax.persistence.JoinColumn(name="UserStatusID", referencedColumnName="UserStatusID")})
	private UserStatus userStatus;

	@javax.persistence.Column(name = "UserID")
	@javax.persistence.Id
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

	@javax.persistence.Column(name = "LastLoginDate")
	@javax.persistence.Basic
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public UserAttribute getUserAttribute() {
		return userAttribute;
	}

	public void setUserAttribute(UserAttribute userAttribute) {
		this.userAttribute = userAttribute;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public List<UserGroup> getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public List<Channel> getChannel() {
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