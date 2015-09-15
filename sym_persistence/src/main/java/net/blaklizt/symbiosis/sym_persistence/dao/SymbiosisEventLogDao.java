package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisEventLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
@Repository
public interface SymbiosisEventLogDao {

    List<SymbiosisEventLog> findByUserID(Long userId);

    List<SymbiosisEventLog> findCoreAndUserID(Long userId);
}
