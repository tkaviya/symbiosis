package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisOption;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisOptionDao {
    SymbiosisOption findByDescription(String description);
}
