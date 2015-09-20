package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.complex_type.symbiosis_auth_user;
import org.springframework.stereotype.Repository;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */

public interface SymbiosisAuthUserDao extends SymbiosisDaoInterface {
    symbiosis_auth_user findByUserIDAndChannel(Long userID, int channelID);
}
