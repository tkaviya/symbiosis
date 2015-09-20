package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.enumeration.symbiosis_option;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisRoleDao extends SymbiosisDaoInterface {
    List<symbiosis_option> findEnabled();
}
