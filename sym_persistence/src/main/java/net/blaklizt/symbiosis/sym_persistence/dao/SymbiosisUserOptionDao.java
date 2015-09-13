package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUserOption;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisUserOptionDao {
    SymbiosisUserOption findByUserIDAndOption(Long symbiosisUserID, Long symbiosisOptionID);
}
