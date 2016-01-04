package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_event_type;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */

@Entity
public class symbiosis_event_log extends symbiosis_entity<symbiosis_event_log> {
	private Long symbiosis_user_id;
	private Long auth_user_id;
	private symbiosis_system system;
	private symbiosis_channel channel;
	private symbiosis_event_type event_type;
	private symbiosis_response_code response_code;
	private Date event_date;
	private String description;

	public Long getSymbiosis_user_id() {
		return symbiosis_user_id;
	}

	public void setSymbiosis_user_id(Long symbiosis_user_id) {
		this.symbiosis_user_id = symbiosis_user_id;
	}

	public symbiosis_event_log(Long auth_user_id, symbiosis_event_type event_type,
							   symbiosis_response_code response_code, Date event_date, String description) {
		this.event_type = event_type;
		this.response_code = response_code;
		this.event_date = event_date;
		this.auth_user_id = auth_user_id;
		this.description = description;
	}

	public symbiosis_event_log(Long symbiosis_user_id, symbiosis_channel channel, symbiosis_event_type event_type,
							   symbiosis_response_code response_code, Date event_date, String description) {
		this.symbiosis_user_id = symbiosis_user_id;
		this.channel = channel;
		this.event_type = event_type;
		this.event_type = event_type;
		this.response_code = response_code;
		this.event_date = event_date;
		this.description = description;
	}

	@Basic
	@Column(name = "auth_user_id", nullable = true)
	public Long getAuth_user_id() {
		return auth_user_id;
	}

	public void setAuth_user_id(Long auth_user_id) {
		this.auth_user_id = auth_user_id;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	public symbiosis_system getSystem() {
		return system;
	}

	public void setSystem(symbiosis_system system) {
		this.system = system;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	public symbiosis_channel getChannel() {
		return channel;
	}

	public void setChannel(symbiosis_channel channel) {
		this.channel = channel;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	public symbiosis_event_type getEvent_type() {
		return event_type;
	}

	public void setEvent_type(symbiosis_event_type event_type) {
		this.event_type = event_type;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	public symbiosis_response_code getResponse_code() {
		return response_code;
	}

	public void setResponse_code(symbiosis_response_code response_code) {
		this.response_code = response_code;
	}

	@Basic
	@Column(name = "event_date", nullable = false)
	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) { this.event_date = event_date; }

	@Basic
	@Column(name = "description", nullable = false, length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
