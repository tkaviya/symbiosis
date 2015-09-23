package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_option;

import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisGroupDao extends SymbiosisEnumEntityDao {
    List<symbiosis_option> findEnabled();
}
