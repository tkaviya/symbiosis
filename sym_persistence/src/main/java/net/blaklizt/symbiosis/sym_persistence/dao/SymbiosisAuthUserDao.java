package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public interface SymbiosisAuthUserDao {

    SymbiosisUser findByUserIDAndChannel(Long userID, int channelID);

}
