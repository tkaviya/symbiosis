package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.enumeration.symbiosis_option;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisEnumEntityDao extends SymbiosisDaoInterface {
    List<symbiosis_option> findEnabled();
    symbiosis_option findEnabledByDescription(String description);
    symbiosis_option findByDescription(String description);
}
