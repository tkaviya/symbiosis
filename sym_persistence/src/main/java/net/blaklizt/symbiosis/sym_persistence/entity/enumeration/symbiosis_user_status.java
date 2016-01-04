package net.blaklizt.symbiosis.sym_persistence.entity.enumeration;

import javax.persistence.Entity;

import static net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code.RESPONSE_CODE_TYPE.ACCOUNT_STATUS;

/**
 * Created with IntelliJ IDEA.
 * User: photon
 * Date: 2015/06/10
 * Time: 3:56 PM
 */
@Entity
public class symbiosis_user_status extends symbiosis_response_code {
	public symbiosis_user_status(Number response_code, String description, String response_message,
								 Boolean enabled, symbiosis_system system) {
		super(response_code, description, response_message, enabled, system, ACCOUNT_STATUS);
	}

	public symbiosis_user_status(Number response_code, String description, String response_message) {
		super(response_code, description, response_message, ACCOUNT_STATUS);
	}
}
