package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisEventLog;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisEventLogDao {

    List<SymbiosisEventLog> findByUserID(Long userId);

    List<SymbiosisEventLog> findCoreAndUserID(Long userId);
}
