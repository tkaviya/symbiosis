package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "SymbiosisAuthUser")
public class SymbiosisAuthUser implements Serializable
{
	private Long symbiosisAuthUserID;
	private Long symbiosisUserID;
	private Integer symbiosisChannelID;
	private Integer symbiosisUserStatusID;
	private String deviceID;
	private String accessSystemID;
	private Date registrationDate;
	private Date lastAuthDate;
	private Date lastLoginDate;
	private SymbiosisChannel symbiosisChannel;
	private SymbiosisUserStatus symbiosisUserStatus;
	private SymbiosisUser symbiosisUser;

	@Id
	@Column(name = "SymbiosisAuthUserID", insertable = false, updatable = false)
	public Long getSymbiosisAuthUserID() {
		return symbiosisAuthUserID;
	}

	public void setSymbiosisAuthUserID(Long symbiosisAuthUserID) {
		this.symbiosisAuthUserID = symbiosisAuthUserID;
	}

	@Column(name = "SymbiosisUserID", insertable = false, updatable = false)
	@Basic
	public Long getSymbiosisUserID() {
		return symbiosisUserID;
	}

	public void setSymbiosisUserID(Long symbiosisUserID) {
		this.symbiosisUserID = symbiosisUserID;
	}

	@Column(name = "SymbiosisChannelID")
	@Basic
	public Integer getSymbiosisChannelID() {
		return symbiosisChannelID;
	}

	public void setSymbiosisChannelID(Integer symbiosisChannelID) {
		this.symbiosisChannelID = symbiosisChannelID;
	}

	@Column(name = "SymbiosisUserStatusID")
	@Basic
	public Integer getSymbiosisUserStatusID() {
		return symbiosisUserStatusID;
	}

	public void setSymbiosisUserStatusID(Integer symbiosisUserStatusID) {
		this.symbiosisUserStatusID = symbiosisUserStatusID;
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

	@JoinTable(name="SymbiosisChannel")
	@JoinColumn(name="SymbiosisChannelID")
	@ManyToOne(targetEntity = SymbiosisChannel.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisChannel getChannel() {
		return symbiosisChannel;
	}

	public void setChannel(SymbiosisChannel symbiosisChannel) {
		this.symbiosisChannel = symbiosisChannel;
	}


	@JoinTable(name="SymbiosisUser")
	@JoinColumn(name="SymbiosisUserID")
	@ManyToOne(targetEntity = SymbiosisUser.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public SymbiosisUser getSymbiosisUser() {
		return symbiosisUser;
	}

	public void setSymbiosisUser(SymbiosisUser symbiosisUser) {
		this.symbiosisUser = symbiosisUser;
	}
}