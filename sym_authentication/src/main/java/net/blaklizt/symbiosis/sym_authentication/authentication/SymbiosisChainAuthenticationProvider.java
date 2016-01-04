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

import net.blaklizt.symbiosis.sym_authentication.security.Security;
import net.blaklizt.symbiosis.sym_core_lib.enumeration.SYM_RESPONSE_CODE;
import net.blaklizt.symbiosis.sym_core_lib.response.ResponseObject;
import net.blaklizt.symbiosis.sym_persistence.entity.complex_type.symbiosis_user;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_event_type;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_system;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_event_log;
import net.blaklizt.symbiosis.sym_persistence.helper.Channel;
import net.blaklizt.symbiosis.sym_persistence.helper.SymbiosisLogHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import static java.util.Arrays.asList;
import static net.blaklizt.symbiosis.sym_common.utilities.Validator.isValidPassword;
import static net.blaklizt.symbiosis.sym_core_lib.enumeration.SYM_RESPONSE_CODE.*;
import static net.blaklizt.symbiosis.sym_persistence.dao.super_class.SymbiosisUserHelper.findByActiveUsername;
import static net.blaklizt.symbiosis.sym_persistence.helper.Channel.WEB;
import static net.blaklizt.symbiosis.sym_persistence.helper.Channel.ANDROID;
import static net.blaklizt.symbiosis.sym_persistence.helper.SymbiosisEnumHelper.getEnumEntity;

public class SymbiosisChainAuthenticationProvider {

	private static final Logger logger = Logger.getLogger(SymbiosisChainAuthenticationProvider.class.getSimpleName());

	private static final Integer MAX_PASSWORD_TRIES = 5;
	private Channel authenticationChannel;
	private final symbiosis_channel symbiosisChannel;
	private final symbiosis_system symbiosisSystem;
	private String username, email, msisdn, password;
	private symbiosis_user symbiosisUser;
	private ResponseObject<symbiosis_user> responseObject;

	private static final HashMap<SYM_RESPONSE_CODE, Object> mappedResponseCode = new HashMap<>();

	private static final HashMap<Channel, ArrayList<AuthenticationStep>> authenticationChain = new HashMap<>();

	private static final HashMap<Channel, Class<? extends SymbiosisChainedAuthentication>> authenticationProviders = new HashMap<>();

	static {

		// We will mask any response code < 0 because it is a general system error that a user should not see
		for (SYM_RESPONSE_CODE symResponseCode : values()) {
			if (symResponseCode.code < 0) { mappedResponseCode.put(symResponseCode, GENERAL_ERROR); }
		}

		// We will mask certain authentication response codes to avoid username/password guessing
		mappedResponseCode.put(DATA_NOT_FOUND,			AUTH_AUTHENTICATION_FAILED);
		mappedResponseCode.put(INPUT_INVALID_REQUEST,	AUTH_AUTHENTICATION_FAILED);
		mappedResponseCode.put(AUTH_INCORRECT_PASSWORD, AUTH_AUTHENTICATION_FAILED);
		mappedResponseCode.put(AUTH_NON_EXISTENT,		AUTH_AUTHENTICATION_FAILED);

		authenticationProviders.put(WEB, SymbiosisWebAuthentication.class);
		authenticationProviders.put(ANDROID, SymbiosisAndroidAuthentication.class);
	}

	protected interface SymbiosisChainedAuthentication {

		default void addMappedResponseCode(SYM_RESPONSE_CODE symResponseCode, Object returnResponse) {
			mappedResponseCode.put(symResponseCode, returnResponse);
		}

		default Object getMappedResponseCode(SYM_RESPONSE_CODE symResponseCode) {
			return mappedResponseCode.get(symResponseCode);
		}

		SymbiosisChainedAuthentication getInstance(symbiosis_system system, symbiosis_channel channel);
	}

	public static class SymbiosisWebAuthentication implements SymbiosisChainedAuthentication {

		private SymbiosisChainAuthenticationProvider symbiosisChainAuthenticationProvider;

		private SymbiosisWebAuthentication() {}

		public ResponseObject<symbiosis_user> authenticate(String username, String password) {
			return symbiosisChainAuthenticationProvider.authenticateWeb(username, password);
		}

		@Override
		public SymbiosisChainedAuthentication getInstance(symbiosis_system system, symbiosis_channel channel) {
			symbiosisChainAuthenticationProvider = new SymbiosisChainAuthenticationProvider(system, channel);
			return new SymbiosisWebAuthentication();
		}
	}

	public static class SymbiosisAndroidAuthentication implements SymbiosisChainedAuthentication {

		private SymbiosisChainAuthenticationProvider symbiosisChainAuthenticationProvider;

		private SymbiosisAndroidAuthentication() {}

		public ResponseObject<symbiosis_user> authenticate(String username, String password) {
			return symbiosisChainAuthenticationProvider.authenticateAndroid(username, password);
		}

		@Override
		public SymbiosisChainedAuthentication getInstance(symbiosis_system system, symbiosis_channel channel) {
			symbiosisChainAuthenticationProvider = new SymbiosisChainAuthenticationProvider(system, channel);
			return new SymbiosisAndroidAuthentication();
		}
	}

	protected ResponseObject<symbiosis_user> authenticateWeb(String username, String password) {
		this.username = username;
		this.password = password;
		return authenticate();
	}

	protected ResponseObject<symbiosis_user> authenticateAndroid(String msisdn, String password) {
		this.msisdn = msisdn;
		this.password = password;
		return authenticate();
	}

	private SymbiosisChainAuthenticationProvider(symbiosis_system system, symbiosis_channel channel) {
		this.symbiosisChannel = channel;
		this.symbiosisSystem = system;
		initializeAuthenticationChain();
	}

	public static SymbiosisChainedAuthentication getAuthenticationProvider(Channel authenticationChannel) {
		switch (authenticationChannel) {
			case WEB: return new SymbiosisWebAuthentication();
			case ANDROID: return new SymbiosisAndroidAuthentication();
		}
		return null;
	}

	public void addMappedResponseCode(SYM_RESPONSE_CODE symResponseCode, Object returnResponse) {

	}

	private interface AuthenticationStep {
		ResponseObject<symbiosis_user> executeAuthenticationStep();
	}

	private void initializeAuthenticationChain() {
		authenticationChain.put(WEB, new ArrayList<>(asList((AuthenticationStep)
			this::getUserByUsernameAndChannel,
			this::validatePassword)));
	}

	public ResponseObject<symbiosis_user> authenticate() {
		ArrayList<AuthenticationStep> chain = authenticationChain.get(symbiosisChannel);

		for (AuthenticationStep authenticationStep : chain) {
			responseObject = authenticationStep.executeAuthenticationStep();
			symbiosisUser = responseObject.getResponseObject();
			if (responseObject.getResponseCode() != SUCCESS) {
				logger.info("Authentication failed with response: " +
					responseObject.getResponseCode().name() + " -> " +
					responseObject.getResponseCode().message);
				break;
			}
		}
		return logAndReturn();

	}

	public ResponseObject<symbiosis_user> authenticate(String msisdn, String password) {
		this.msisdn = msisdn;
		this.password = password;
		return authenticate();
	}



	private ResponseObject<symbiosis_user> getUserByUsernameAndChannel() {
		return findByActiveUsername(username, symbiosisSystem, symbiosisChannel);
	}

	private ResponseObject<symbiosis_user> validatePassword() {

		ResponseObject<symbiosis_user> response = new ResponseObject<>(GENERAL_ERROR, symbiosisUser);

		if (symbiosisUser == null) {
			response.setResponseCode(INPUT_INVALID_REQUEST).setMessage("User was null");
		}
		else if (symbiosisUser.getUser_status().getId() != ACC_ACTIVE.code) {
			response.setResponseCode(SYM_RESPONSE_CODE.valueOf(symbiosisUser.getUser_status().getId().intValue()));
		}
		else {
			int passwordTries = symbiosisUser.getPassword_tries();

			if (passwordTries >= MAX_PASSWORD_TRIES) {
				response.setResponseCode(ACC_PASSWORD_TRIES_EXCEEDED);
			}
			else if (!isValidPassword(password)) {
				response.setResponseCode(INPUT_INVALID_REQUEST).setMessage("Password format was invalid");
				symbiosisUser.setPassword_tries(++passwordTries);
			}
			else if (symbiosisUser.getPassword().equals(encyptPassword(password, symbiosisUser.getSalt()))) {
				response.setResponseCode(SUCCESS);
				symbiosisUser.setPassword_tries(0);
			}
			else {
				response.setResponseCode(AUTH_INCORRECT_PASSWORD);
				symbiosisUser.setPassword_tries(++passwordTries);
			}
		}
		return response;
	}

	private ResponseObject<symbiosis_user> logAndReturn() {

		symbiosis_event_log log = new symbiosis_event_log(symbiosisUser.getId(), symbiosisChannel, new symbiosis_event_type(),
			(symbiosis_response_code) getEnumEntity(symbiosis_response_code.class, (long) responseObject.getResponseCode().code),
			new Date(), responseObject.getMessage());

		SymbiosisLogHelper.logSystemEvent(log);

		if (responseObject.getResponseCode() != SUCCESS) {
			Object mappedResponse = mappedResponseCode.get(responseObject.getResponseCode());
			if (mappedResponse != null && mappedResponse instanceof SYM_RESPONSE_CODE) {
				logger.info("Returning response " + mappedResponse + " for response code " + responseObject.getResponseCode());
				responseObject.setResponseCode((SYM_RESPONSE_CODE) mappedResponse);
			}
		}
		return responseObject;
	}

	public static String encyptPassword(String rawPassword, String salt) {
		logger.info("Encrypting [ " + rawPassword + " with salt " + salt + " ]");
		String encryptedPassword = Security.encryptWithSalt(rawPassword, "SHA512", salt.getBytes());
		logger.info("Encrypted password: " + encryptedPassword);
		return encryptedPassword;
	}
}

