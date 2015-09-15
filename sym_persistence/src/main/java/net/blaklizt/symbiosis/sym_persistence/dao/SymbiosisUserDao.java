package net.blaklizt.symbiosis.sym_persistence.dao;

import net.blaklizt.symbiosis.sym_persistence.SymbiosisUser;
import org.springframework.stereotype.Repository;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
@Repository
public interface SymbiosisUserDao {
    SymbiosisUser findByUsername(String username);

    SymbiosisUser findByEmail(String email);

    SymbiosisUser findByMsisdn(String msisdn);
}
