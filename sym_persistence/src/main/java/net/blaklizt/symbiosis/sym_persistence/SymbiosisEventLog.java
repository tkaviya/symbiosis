package net.blaklizt.symbiosis.sym_persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class SymbiosisEventLog {
	private Long symbiosisEventLogId;
	private Long symbiosisEventTypeId;
	private Timestamp eventDate;
	private Long symbiosisUserId;
	private String description;

	@Id
	@Column(name = "SymbiosisEventLogID", nullable = false, insertable = true, updatable = true)
	public Long getSymbiosisEventLogId() {
		return symbiosisEventLogId;
	}

	public void setSymbiosisEventLogId(Long symbiosisEventLogId) {
		this.symbiosisEventLogId = symbiosisEventLogId;
	}

	@Basic
	@Column(name = "SymbiosisEventTypeID", nullable = true, insertable = true, updatable = true)
	public Long getSymbiosisEventTypeId() {
		return symbiosisEventTypeId;
	}

	public void setSymbiosisEventTypeId(Long symbiosisEventTypeId) {
		this.symbiosisEventTypeId = symbiosisEventTypeId;
	}

	@Basic
	@Column(name = "EventDate", nullable = false, insertable = true, updatable = true)
	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	@Basic
	@Column(name = "SymbiosisUserID", nullable = false, insertable = true, updatable = true)
	public Long getSymbiosisUserId() {
		return symbiosisUserId;
	}

	public void setSymbiosisUserId(Long symbiosisUserId) {
		this.symbiosisUserId = symbiosisUserId;
	}

	@Basic
	@Column(name = "Description", nullable = false, insertable = true, updatable = true, length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
