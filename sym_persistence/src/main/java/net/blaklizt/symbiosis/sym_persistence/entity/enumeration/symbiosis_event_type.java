package net.blaklizt.symbiosis.sym_persistence.entity.enumeration;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_event_type extends symbiosis_enum_entity<symbiosis_event_type> {
	public symbiosis_event_type(String description, Boolean enabled) {
		super(description, enabled);
	}
}
