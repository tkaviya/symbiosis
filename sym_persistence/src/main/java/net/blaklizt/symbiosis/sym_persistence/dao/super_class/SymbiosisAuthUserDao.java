package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */

public interface SymbiosisAuthUserDao extends SymbiosisDaoInterface {
    symbiosis_auth_user findByUserIDAndChannel(Long userID, Long channelID);
}
