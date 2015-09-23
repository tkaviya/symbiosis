package net.blaklizt.symbiosis.sym_persistence.entity.simple_type;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
@Table(name = "symbiosis_event_log")
public class symbiosis_event_log extends symbiosis_entity {
	private Long symbiosis_event_type_id;
	private Date event_date;
	private Long symbiosis_user_id;
	private String description;

	@Basic
	@Column(name = "symbiosis_event_type_id", nullable = false)
	public Long getSymbiosis_event_type_id() {
		return symbiosis_event_type_id;
	}

	public void setSymbiosis_event_type_id(Long symbiosis_event_type_id) {
		this.symbiosis_event_type_id = symbiosis_event_type_id;
	}

	@Basic
	@Column(name = "event_date", nullable = false)
	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) { this.event_date = event_date; }

	@Basic
	@Column(name = "symbiosis_user_id", nullable = false)
	public Long getSymbiosis_user_id() {
		return symbiosis_user_id;
	}

	public void setSymbiosis_user_id(Long symbiosis_user_id) {
		this.symbiosis_user_id = symbiosis_user_id;
	}

	@Basic
	@Column(name = "description", nullable = false, length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
