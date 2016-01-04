package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_enum_entity;

/**
 * Created by tkaviya on 9/13/2015.
 */
public enum Channel implements AbstractEnumHelper<symbiosis_channel> {

    ANDROID;

    public Class<? extends symbiosis_enum_entity> getEnumEntityClass() { return symbiosis_channel.class; }

    public Long value()  { return SymbiosisEnumHelper.getEnumHelper(getEnumEntityClass()).getID(this); }
}
