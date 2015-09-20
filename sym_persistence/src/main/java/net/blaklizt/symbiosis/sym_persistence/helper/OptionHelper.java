package net.blaklizt.symbiosis.sym_persistence.helper;

import net.blaklizt.symbiosis.sym_persistence.dao.SymbiosisEnumEntityDao;

/**
 * Created by tkaviya on 9/13/2015.
 */
public enum OptionHelper {

    SYNC_FOLDER { @Override public Long value() { return getOptionDBHelper().getMappedID(SYNC_FOLDER); } };

    SymbiosisDBEnum<SymbiosisEnumEntityDao> optionDBHelper;

    @SuppressWarnings("unchecked")
    SymbiosisDBEnum getOptionDBHelper() {
        if (optionDBHelper == null)
            optionDBHelper = new SymbiosisDBEnum(DaoManager.getInstance().getOptionDao());
        return optionDBHelper;
    }

    public abstract Long value();
}
