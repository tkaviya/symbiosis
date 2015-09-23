package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.simple_type.symbiosis_user_option;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisUserOptionDao extends SymbiosisDaoInterface {
    symbiosis_user_option findByUserIDAndOption(Long symbiosisUserID, Long symbiosisOptionID);
}
