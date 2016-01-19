package net.blaklizt.symbiosis.sym_authentication.authentication;

/* *************************************************************************
 * Created:     2016/01/01                                                 *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *************************************************************************
*/

import net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_auth_user;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_event_type;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_event_log;
import net.blaklizt.symbiosis.sym_persistence.helper.SymbiosisLogHelper;
import net.blaklizt.symbiosis.sym_persistence.structure.ResponseObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator.getAuthUserByUserId;
import static net.blaklizt.symbiosis.sym_authentication.authentication.SymbiosisAuthenticator.getUserByUsername;
import static net.blaklizt.symbiosis.sym_persistence.admin.SymbiosisConfig.*;

public abstract class SymbiosisChainAuthenticationProvider {

	protected static final Logger logger = Logger.getLogger(SymbiosisChainAuthenticationProvider.class.getSimpleName());
	protected symbiosis_event_type symbiosisEventType;
	protected final symbiosis_channel symbiosisChannel;
	protected final symbiosis_system symbiosisSystem;
	private symbiosis_user symbiosisUser;
	private symbiosis_auth_user symbiosisAuthUser;
	private ResponseObject<symbiosis_user> responseObject;
	private String username, email, msisdn, password;

	protected static final HashMap<symbiosis_channel, ArrayList<AuthenticationStep>> authenticationChain = new HashMap<>();

	private static final HashMap<symbiosis_response_code, Object> mappedResponseCode = new HashMap<>();

	static {
		// We will mask any response code < 0 because it is a general system error that a user should not see
		for (symbiosis_response_code symResponseCode : SymbiosisConfig.getAllResponseCodes()) {
			if (symResponseCode.getId() < 0) { mappedResponseCode.put(symResponseCode, GENERAL_ERROR); }
		}

		// We will mask certain authentication response codes to avoid username/password guessing
		mappedResponseCode.put(DATA_NOT_FOUND,			AUTH_AUTHENTICATION_FAILED);
		mappedResponseCode.put(INPUT_INVALID_REQUEST,	AUTH_AUTHENTICATION_FAILED);
		mappedResponseCode.put(AUTH_INCORRECT_PASSWORD, AUTH_AUTHENTICATION_FAILED);
		mappedResponseCode.put(AUTH_NON_EXISTENT,		AUTH_AUTHENTICATION_FAILED);
	}

	protected interface AuthenticationStep {
		ResponseObject<symbiosis_user> executeAuthenticationStep();
	}

	//each auth provider must determine its own chain of authentication
	protected abstract void initializeAuthenticationChain();

	public SymbiosisChainAuthenticationProvider(symbiosis_system system, symbiosis_channel channel) {
		this.symbiosisChannel = channel;
		this.symbiosisSystem = system;
		initializeAuthenticationChain();
	}

	// Functions to set auth data. They return SymbiosisChainAuthenticationProvider simply to allow method chaining
	protected SymbiosisChainAuthenticationProvider setAuthUsername(String username) { this.username = username; return this; }

	protected SymbiosisChainAuthenticationProvider setAuthEmail(String email) { this.email = email; return this; }

	protected SymbiosisChainAuthenticationProvider setAuthMsisdn(String msisdn) { this.msisdn = msisdn; return this; }

	protected SymbiosisChainAuthenticationProvider setAuthPassword(String password) { this.password = password; return this; }

	public static void addMappedResponseCode(symbiosis_response_code symResponseCode, Object returnResponse) {
		mappedResponseCode.put(symResponseCode, returnResponse);
	}

	public static Object getMappedResponseCode(symbiosis_response_code symResponseCode) {
		return mappedResponseCode.get(symResponseCode);
	}

	public final ResponseObject<symbiosis_user> authenticateUser() {

		symbiosisEventType = LOGIN;

		ArrayList<AuthenticationStep> chain = authenticationChain.get(symbiosisChannel);

		for (AuthenticationStep authenticationStep : chain) {
			responseObject = authenticationStep.executeAuthenticationStep();
			if (responseObject.getResponseCode() != SUCCESS) {
				logger.info("Authentication failed with response: " +
					responseObject.getResponseCode().getDescription() + " -> " +
					responseObject.getResponseCode().getResponse_message());
				break;
			}
		}
		return logAndReturn();

	}

	protected ResponseObject<symbiosis_user> getUserByUsernameAndChannel() {
		ResponseObject<symbiosis_user> userResponse = getUserByUsername(username, symbiosisSystem, symbiosisChannel);

		if (userResponse.getResponseCode() == SUCCESS) {
			symbiosisUser = responseObject.getResponseObject();

			ResponseObject<symbiosis_auth_user> authUserResponse =
				getAuthUserByUserId(symbiosisUser.getId(), symbiosisSystem, symbiosisChannel);

			symbiosisAuthUser = authUserResponse.getResponseObject();

			userResponse.setResponseCode(authUserResponse.getResponseCode());

		} else { return userResponse; }

		return userResponse;
	}

	protected ResponseObject<symbiosis_user> validatePassword() {
		ResponseObject<symbiosis_auth_user> authUserResponse = SymbiosisAuthenticator.validatePassword(symbiosisAuthUser, password);
		return new ResponseObject<>(authUserResponse.getResponseCode(), authUserResponse.getResponseObject().getUser());
	}

	protected ResponseObject<symbiosis_user> logAndReturn() {

		symbiosis_event_log log = new symbiosis_event_log(symbiosisUser.getId(), symbiosisChannel, symbiosisEventType,
			responseObject.getResponseCode(), new Date(), responseObject.getMessage());

		SymbiosisLogHelper.logSystemEvent(log);

		if (responseObject.getResponseCode() != SUCCESS) {
			Object mappedResponse = mappedResponseCode.get(responseObject.getResponseCode());
			if (mappedResponse != null && mappedResponse instanceof symbiosis_response_code) {
				logger.info("Returning response " + mappedResponse + " for response code " + responseObject.getResponseCode());
				responseObject.setResponseCode((symbiosis_response_code) mappedResponse);
			}
		}
		return responseObject;
	}
}

