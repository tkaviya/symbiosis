package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisOption;
import net.blaklizt.symbiosis.sym_persistence.dao.impl.SymbiosisOptionDaoImpl;
import net.blaklizt.symbiosis.sym_persistence.enumeration.SymbiosisDBEnum;

/**
 * Created by tkaviya on 9/13/2015.
 */
public enum OptionHelper {

    SYNC_FOLDER;

    final SymbiosisDBEnum<SymbiosisOption, Long, SymbiosisOptionDaoImpl> optionDBHelper = new SymbiosisDBEnum();

    public Long value() { return optionDBHelper.getMappedID(this); }
}
