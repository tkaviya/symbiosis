package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_common.structure.Pair;
import net.blaklizt.symbiosis.sym_core_lib.response.ResponseObject;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;

import static net.blaklizt.symbiosis.sym_common.structure.Pair.p;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisEntityManager.DaoDataManager.using;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public class SymbiosisUserHelper {

	static final SymbiosisEntityManager<symbiosis_user, Long> symbiosisUserDao = using(symbiosis_user.class);

	public static ResponseObject<symbiosis_user> findByUsername(String username) {
		return symbiosisUserDao.enforceUnique(symbiosisUserDao.findWhere(true, 1,
			p("username", username), p("symbiosis_user_status_id", 1)));
	}

	public static ResponseObject<symbiosis_user> findByEmail(String email) {
		return symbiosisUserDao.enforceUnique(symbiosisUserDao.findWhere(true, 1, new Pair<String, Object>("email", email)));
	}

	public static ResponseObject<symbiosis_user> findByMsisdn(String msisdn) {
		return symbiosisUserDao.enforceUnique(symbiosisUserDao.findWhere(true, 1, new Pair<String, Object>("msisdn", msisdn)));
	}
}
