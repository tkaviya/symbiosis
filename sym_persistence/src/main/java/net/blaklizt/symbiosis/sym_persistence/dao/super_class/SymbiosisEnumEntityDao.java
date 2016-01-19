package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_option;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisEnumEntityDao<E extends symbiosis_entity, I extends Serializable>
        extends SymbiosisDaoInterface<E, I> {
    List<symbiosis_option> findEnabled();
    symbiosis_option findEnabledByDescription(String description);
    symbiosis_option findByDescription(String description);
}
