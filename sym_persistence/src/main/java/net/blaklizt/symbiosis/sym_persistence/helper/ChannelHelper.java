package net.blaklizt.symbiosis.sym_persistence.helper;

/**
 * Created by tkaviya on 9/13/2015.
 */
public enum ChannelHelper implements AbstractEnumHelper {

    ANDROID;

    public Long value()  { return SymbiosisDBEnumHelper.getSymbiosisDBEnumHelper(DaoManager.getInstance().getChannelDao()).getMappedID(this); }
}
