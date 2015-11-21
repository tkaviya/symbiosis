package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;

/**
 * Created by tkaviya on 9/13/2015.
 */
public enum ChannelHelper implements AbstractEnumHelper {

    ANDROID;

    public Class getEnumEntityClass() { return symbiosis_channel.class; }

    public Long value()  { return SymbiosisDBEnumHelper.getDaoHelper(DaoManager.getInstance().getChannelDao()).getMappedID(this); }
}
