package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.simple_type.symbiosis_event_log;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisEventLogDao extends SymbiosisDaoInterface{

    List<symbiosis_event_log> findByUserID(Long userId);

    List<symbiosis_event_log> findByUserAndEventTypeID(Long userId, Long eventTypeId);
}
