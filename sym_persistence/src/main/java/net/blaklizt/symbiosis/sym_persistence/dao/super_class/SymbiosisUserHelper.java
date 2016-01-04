package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static net.blaklizt.symbiosis.sym_common.structure.Pair.filterList;
import static net.blaklizt.symbiosis.sym_common.structure.Pair.p;
import static net.blaklizt.symbiosis.sym_common.utilities.Validator.*;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.*;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisEntityManager.DaoDataManager.using;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public class SymbiosisUserHelper {

	static final SymbiosisEntityManager<symbiosis_auth_user, Long> symbiosisAuthUserDao = using(symbiosis_auth_user.class);

	static final SymbiosisEntityManager<symbiosis_user, Long> symbiosisUserDao = using(symbiosis_user.class);

	@SuppressWarnings("unchecked")
	public static ResponseObject<symbiosis_auth_user> findByUserId(
		Long symbiosis_user_id, symbiosis_system system, symbiosis_channel channel, symbiosis_user_status user_status) {
		return symbiosisAuthUserDao.findUniqueWhere(
			filterList(p("symbiosis_user_id", symbiosis_user_id), p("user_status", user_status), p("system", system), p("channel", channel)));
	}

	@SuppressWarnings("unchecked")
	public static ResponseObject<symbiosis_user> findByActiveUsername(
		String username, symbiosis_system system, symbiosis_channel channel) {
		if (isValidUsername(username)) { return new ResponseObject<>(INPUT_INVALID_USERNAME); }
		return symbiosisUserDao.findUniqueWhere(
			filterList(p("username", username), p("user_status", ACC_ACTIVE), p("system", system), p("channel", channel)));
	}

	@SuppressWarnings("unchecked")
	public static ResponseObject<symbiosis_user> findByActiveEmail(
		String email, symbiosis_system system, symbiosis_channel channel) {
		if (isValidEmail(email)) { return new ResponseObject<>(INPUT_INVALID_EMAIL); }
		return symbiosisUserDao.findUniqueWhere(
			filterList(p("email", email), p("user_status", ACC_ACTIVE), p("system", system), p("channel", channel)));
	}

	@SuppressWarnings("unchecked")
	public static ResponseObject<symbiosis_user> findByActiveMsisdn(
		String msisdn, symbiosis_system system, symbiosis_channel channel) {
		if (isValidMsisdn(msisdn)) { return new ResponseObject<>(INPUT_INVALID_MSISDN); }
		return symbiosisUserDao.findUniqueWhere(
			filterList(p("msisdn", msisdn), p("user_status", ACC_ACTIVE), p("system", system), p("channel", channel)));
	}
}
