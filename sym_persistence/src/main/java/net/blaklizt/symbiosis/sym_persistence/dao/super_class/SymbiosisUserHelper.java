package net.blaklizt.symbiosis.sym_persistence.dao.super_class;

import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import static net.blaklizt.symbiosis.sym_common.structure.Pair.filterList;
import static net.blaklizt.symbiosis.sym_common.structure.Pair.p;
import static net.blaklizt.symbiosis.sym_common.utilities.Validator.*;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.*;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.GenericDao.findUniqueWhere;

/**
 * Created by tsungai.kaviya on 2015-08-24.
 */
public class SymbiosisUserHelper {

	public static ResponseObject<symbiosis_auth_user> findByUserId(
		Long symbiosis_user_id, symbiosis_system system, symbiosis_channel channel, symbiosis_user_status user_status) {
		return findUniqueWhere(symbiosis_auth_user.class,
			filterList(p("symbiosis_user_id", symbiosis_user_id), p("user.user_status", user_status), p("system", system), p("channel", channel)));
	}

	public static ResponseObject<symbiosis_user> findByActiveUsername(
		String username, symbiosis_system system, symbiosis_channel channel) {
		if (isValidUsername(username)) {
			return findUniqueWhere(symbiosis_user.class,
				filterList(p("username", username), p("user_status", ACC_ACTIVE), p("system", system), p("channel", channel)));
		} else if (isValidEmail(username))	{ return findByActiveEmail(username, system, channel); }
		  else if (isValidMsisdn(username))	{ return findByActiveMsisdn(username, system, channel); }
		  else 								{ return new ResponseObject<>(INPUT_INVALID_USERNAME); }
	}

	public static ResponseObject<symbiosis_user> findByActiveEmail(
		String email, symbiosis_system system, symbiosis_channel channel) {
		if (!isValidEmail(email)) { return new ResponseObject<>(INPUT_INVALID_EMAIL); }
		return findUniqueWhere(symbiosis_user.class,
			filterList(p("email", email), p("user_status", ACC_ACTIVE), p("system", system), p("channel", channel)));
	}

	public static ResponseObject<symbiosis_user> findByActiveMsisdn(
		String msisdn, symbiosis_system system, symbiosis_channel channel) {
		if (!isValidMsisdn(msisdn)) { return new ResponseObject<>(INPUT_INVALID_MSISDN); }
		return findUniqueWhere(symbiosis_user.class,
			filterList(p("msisdn", msisdn), p("user_status", ACC_ACTIVE), p("system", system), p("channel", channel)));
	}
}
