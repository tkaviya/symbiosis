package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

/**
 * Created by tkaviya on 9/13/2015.
 */
public enum Channel implements SymbiosisEnumType<symbiosis_channel> {

    WEB, ANDROID;

	@Override
    public Class<? extends symbiosis_enum_entity> getEnumEntityClass() { return symbiosis_channel.class; }
}
