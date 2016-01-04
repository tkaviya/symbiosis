package net.blaklizt.symbiosis.sym_persistence.entity.enumeration;

import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_complex_enum;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_channel extends symbiosis_complex_enum<symbiosis_channel> {
	public symbiosis_channel(String description, Boolean enabled) {
		super(description, enabled);
	}
//	public symbiosis_channel(String description, Boolean enabled,
//		ArrayList<symbiosis_channel> groupItems, symbiosis_channel group) {
//		super(description, enabled, groupItems, group);
//	}
}