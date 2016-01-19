package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */

public interface SymbiosisUserDao extends SymbiosisDaoInterface {
    symbiosis_user findByUsername(String username);

    symbiosis_user findByEmail(String email);

    symbiosis_user findByMsisdn(String msisdn);
}
