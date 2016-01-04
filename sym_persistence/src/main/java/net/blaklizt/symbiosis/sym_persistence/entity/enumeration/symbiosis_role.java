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
public class symbiosis_role extends symbiosis_enum_entity<symbiosis_role> {
	public symbiosis_role(String description, Boolean enabled) {
		super(description, enabled);
	}
}
