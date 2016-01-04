package net.blaklizt.symbiosis.sym_persistence.entity.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_event_type;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.DiscriminatorType.INTEGER;

/**
 * Created with Intelli_j IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */

@MappedSuperclass
@DiscriminatorColumn(discriminatorType = INTEGER, name = "system")
public class symbiosis_event_log extends symbiosis_entity {

	private symbiosis_system system;
	private symbiosis_event_type event_type;
	private symbiosis_response_code response_code;
	private Date event_date;
	private Long symbiosis_user_id;
	private String description;

	@OneToMany
	public symbiosis_system getSystem() {
		return system;
	}

	public void setSystem(symbiosis_system system) {
		this.system = system;
	}

	@OneToMany
	public symbiosis_event_type getEvent_type() {
		return event_type;
	}

	@OneToMany
	public symbiosis_response_code getResponse_code() {
		return response_code;
	}

	public void setResponse_code(symbiosis_response_code response_code) {
		this.response_code = response_code;
	}

	public void setEvent_type(symbiosis_event_type event_type) {
		this.event_type = event_type;
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
