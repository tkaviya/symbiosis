package net.blaklizt.symbiosis.sym_persistence.admin;

/* *************************************************************************
 * Created:     2016/01/04                                                 *
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

import net.blaklizt.symbiosis.sym_persistence.entity.config.symbiosis_country;
import net.blaklizt.symbiosis.sym_persistence.entity.config.symbiosis_system_role;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.*;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code.RESPONSE_CODE_TYPE;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code.RESPONSE_CODE_TYPE.AUTHENTICATION;
import static net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code.RESPONSE_CODE_TYPE.CONNECTIVITY;
import static net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code.RESPONSE_CODE_TYPE.INPUT_VALIDATION;

public final class SymbiosisConfig {

	private SymbiosisConfig() {}

	public static final symbiosis_channel
//	WEB = new symbiosis_channel("WEB",		true, null, null),
//	 MOBILE = new symbiosis_channel("MOBILE",	true, null, null),
//	ANDROID = new symbiosis_channel("ANDROID",	true, null, MOBILE);
	WEB = new symbiosis_channel("WEB",		true),
	 MOBILE = new symbiosis_channel("MOBILE",	true),
	ANDROID = new symbiosis_channel("ANDROID",	true);

	public static final symbiosis_country
		SOUTH_AFRICA = new symbiosis_country("SOUTH_AFRICA", true, "South Africa", "27"),
			ZIMBABWE = new symbiosis_country("ZIMBABWE",	 true, "Zimbabwe",	   "263");

	public static final symbiosis_event_type
		REGISTRATION = new symbiosis_event_type("REGISTRATION",	true),
			   LOGIN = new symbiosis_event_type("LOGIN",		true);

	public static final symbiosis_group
		SYMBIOSIS_ADMIN = 	new symbiosis_group("SYMBIOSIS_ADMIN",	true),
		SYMBIOSIS_EDITOR =	new symbiosis_group("SYMBIOSIS_EDITOR",	false),
		SYMBIOSIS_USER =	new symbiosis_group("SYMBIOSIS_USER",	false),
		STREETS_ADMIN =		new symbiosis_group("STREETS_ADMIN",	false),
		STREETS_EDITOR =	new symbiosis_group("STREETS_EDITOR",	false),
		STREETS_USER =		new symbiosis_group("STREETS_USER",		true);

	public static final symbiosis_language ENGLISH = new symbiosis_language("ENGLISH", true);

	public static final symbiosis_system
		SYMBIOSIS = new symbiosis_system("SYMBIOSIS",	true),
		STREETS =	new symbiosis_system("STREETS",		true);

	public static final symbiosis_role
		ROLE_ADMIN = 	new symbiosis_role("ROLE_ADMIN",	true),
		ROLE_EDITOR =	new symbiosis_role("ROLE_EDITOR",	true),
		ROLE_USER =		new symbiosis_role("ROLE_USER",		false);

	public static final symbiosis_system_role
		SYMBIOSIS_SYSTEM_ADMIN	= new symbiosis_system_role("SYMBIOSIS_SYSTEM_ADMIN",	true, SYMBIOSIS,ROLE_ADMIN),
		STREETS_SYSTEM_ADMIN	= new symbiosis_system_role("STREETS_SYSTEM_ADMIN", 	true, STREETS,	ROLE_ADMIN),
		SYMBIOSIS_SYSTEM_EDITOR	= new symbiosis_system_role("SYMBIOSIS_SYSTEM_EDITOR",	true, SYMBIOSIS,ROLE_EDITOR),
		STREETS_SYSTEM_EDITOR	= new symbiosis_system_role("STREETS_SYSTEM_EDITOR",	true, STREETS,	ROLE_EDITOR),
		SYMBIOSIS_SYSTEM_USER	= new symbiosis_system_role("SYMBIOSIS_SYSTEM_USER",	true, SYMBIOSIS,ROLE_USER),
		STREETS_SYSTEM_USER		= new symbiosis_system_role("STREETS_SYSTEM_USER",		true, STREETS,	ROLE_USER);

	ArrayList<symbiosis_system_role> SYMBIOSIS_ADMIN_ROLE_LIST = new ArrayList<>(Arrays.asList(SYMBIOSIS_SYSTEM_ADMIN, STREETS_SYSTEM_ADMIN,
		SYMBIOSIS_SYSTEM_EDITOR, STREETS_SYSTEM_EDITOR,
		SYMBIOSIS_SYSTEM_USER, STREETS_SYSTEM_USER));

//	public static final symbiosis_group_system_role
//		/* SYMBIOSIS_ADMIN is super user on every system. In fact, his privileges should be hard coded */
//		SYMBIOSIS_ADMIN_ROLES = new symbiosis_group_system_role(	"SYMBIOSIS_ADMIN_ROLES",	true, SYMBIOSIS_ADMIN, new ArrayList<>(
//			asList(SYMBIOSIS_SYSTEM_ADMIN, STREETS_SYSTEM_ADMIN,
//				SYMBIOSIS_SYSTEM_EDITOR, STREETS_SYSTEM_EDITOR,
//				SYMBIOSIS_SYSTEM_USER, STREETS_SYSTEM_USER))),
//		SYMBIOSIS_EDITOR_ROLES = new symbiosis_group_system_role(	"SYMBIOSIS_EDITOR_ROLES",	true, SYMBIOSIS_EDITOR, new ArrayList<>(
//			asList(	SYMBIOSIS_SYSTEM_EDITOR,STREETS_SYSTEM_EDITOR,
//					SYMBIOSIS_SYSTEM_USER,	STREETS_SYSTEM_USER))),
//		SYMBIOSIS_USER_ROLES = new symbiosis_group_system_role(		"SYMBIOSIS_USER_ROLES",		true, SYMBIOSIS_USER, new ArrayList<>(
//			asList( SYMBIOSIS_SYSTEM_USER, STREETS_SYSTEM_USER))),
//		STREETS_ADMIN_ROLES = new symbiosis_group_system_role(		"STREETS_ADMIN_ROLES",		true, STREETS_ADMIN, new ArrayList<>(
//			asList(	STREETS_SYSTEM_ADMIN,
//					STREETS_SYSTEM_EDITOR,
//					STREETS_SYSTEM_USER))),
//		STREETS_EDITOR_ROLES = new symbiosis_group_system_role(		"STREETS_EDITOR_ROLES",		true, STREETS_EDITOR, new ArrayList<>(
//			asList(	STREETS_SYSTEM_EDITOR,
//					STREETS_SYSTEM_USER))),
//		STREETS_USER_ROLES = new symbiosis_group_system_role(		"STREETS_USER_ROLES",		true, STREETS_USER, new ArrayList<>(
//			asList(	STREETS_SYSTEM_USER)));

	public static final symbiosis_response_code

	/* Generic response codes */
	CONFIGURATION_INVALID = new symbiosis_response_code(-1,	"CONFIGURATION_INVALID", "Specified configuration is not valid"),

	SUCCESS				  = new symbiosis_response_code(0,	"SUCCESS",				 "Success"),
	GENERAL_ERROR		  = new symbiosis_response_code(1,	"GENERAL_ERROR",		 "An internal system error occurred"),
	DATA_NOT_FOUND		  = new symbiosis_response_code(2,	"DATA_NOT_FOUND",		 "Data does not exist"),

	/* Input validation errors */
	INPUT_INCOMPLETE_REQUEST = new symbiosis_response_code(22, "INPUT_INCOMPLETE_REQUEST",	"Incomplete request specified", INPUT_VALIDATION),
	INPUT_INVALID_REQUEST	 = new symbiosis_response_code(23, "INPUT_INVALID_REQUEST",	  	"Invalid request specified", INPUT_VALIDATION),
	INPUT_INVALID_EMAIL		 = new symbiosis_response_code(24, "INPUT_INVALID_EMAIL",	  	"Email provided was not valid", INPUT_VALIDATION),
	INPUT_INVALID_MSISDN	 = new symbiosis_response_code(25, "INPUT_INVALID_MSISDN",	  	"Phone number provided was not valid", INPUT_VALIDATION),
	INPUT_INVALID_FIRST_NAME = new symbiosis_response_code(26, "INPUT_INVALID_FIRST_NAME",	"First name provided was not valid", INPUT_VALIDATION),
	INPUT_INVALID_LAST_NAME	 = new symbiosis_response_code(27, "INPUT_INVALID_LAST_NAME",	"Last name provided was not valid", INPUT_VALIDATION),
	INPUT_INVALID_USERNAME	 = new symbiosis_response_code(28, "INPUT_INVALID_USERNAME",	"Username provided was not valid", INPUT_VALIDATION),
	INPUT_INVALID_PASSWORD	 = new symbiosis_response_code(29, "INPUT_INVALID_PASSWORD",	"Password provided was not valid", INPUT_VALIDATION),
	INPUT_INVALID_NAME		 = new symbiosis_response_code(30, "INPUT_INVALID_NAME",		"Name provided was not valid", INPUT_VALIDATION),

	/* incorrect auth input errors */
	AUTH_INSUFFICIENT_PRIVILEGES = new symbiosis_response_code(100, "AUTH_INSUFFICIENT_PRIVILEGES",	"Insufficient privileges", AUTHENTICATION),
	AUTH_AUTHENTICATION_FAILED	 = new symbiosis_response_code(101, "AUTH_AUTHENTICATION_FAILED",	"Authentication failed", AUTHENTICATION),
	AUTH_INCORRECT_PASSWORD		 = new symbiosis_response_code(102, "AUTH_INCORRECT_PASSWORD",		"Password is incorrect", AUTHENTICATION),
	AUTH_NON_EXISTENT			 = new symbiosis_response_code(103, "AUTH_NON_EXISTENT",			"Account does not exist", AUTHENTICATION),

	// Registration Codes
	REG_REGISTRATION_FAILED			= new symbiosis_response_code(151, "REG_REGISTRATION_FAILED",	"Registration Failed", RESPONSE_CODE_TYPE.REGISTRATION),
	REG_PREVIOUS_MSISDN_FOUND		= new symbiosis_response_code(152, "REG_PREVIOUS_MSISDN_FOUND",	"Mobile number has been previously registered", RESPONSE_CODE_TYPE.REGISTRATION),
	REG_PREVIOUS_EMAIL_FOUND		= new symbiosis_response_code(153, "REG_PREVIOUS_EMAIL_FOUND",	"Email has been previously registered", RESPONSE_CODE_TYPE.REGISTRATION),
	REG_PREVIOUS_REGISTRATION_FOUND	= new symbiosis_response_code(154, "REG_PREVIOUS_REGISTRATION_FOUND", "Previous registration found", RESPONSE_CODE_TYPE.REGISTRATION);

	/* account status response codes */
	public static final symbiosis_user_status
		ACC_ACTIVE	  = new symbiosis_user_status(130, "ACC_ACTIVE",	"Account is active"),
		ACC_INACTIVE  = new symbiosis_user_status(131, "ACC_INACTIVE",	"Account is inactive"),
		ACC_SUSPENDED = new symbiosis_user_status(132, "ACC_SUSPENDED",	"Account has been suspended"),
		ACC_CLOSED	  = new symbiosis_user_status(133, "ACC_CLOSED",	"Account has been closed"),
		ACC_PASSWORD_TRIES_EXCEEDED = new symbiosis_user_status(134, "ACC_PASSWORD_TRIES_EXCEEDED", "Password tries exceeded"),
		ACC_PASSWORD_EXPIRED		= new symbiosis_user_status(135, "ACC_PASSWORD_EXPIRED",		"Password expired");

	/* connectivity response codes */
	public static final symbiosis_response_code
		CON_CONNECTION_FAILED	= new symbiosis_response_code(40, "CON_CONNECTION_FAILED",	"Connection failed", CONNECTIVITY),
		CON_UNKNOWN_HOST		= new symbiosis_response_code(41, "CON_UNKNOWN_HOST",		"Unknown host", CONNECTIVITY),
		CON_CONNECTION_REFUSED	= new symbiosis_response_code(42, "CON_CONNECTION_REFUSED",	"Connection Refused", CONNECTIVITY),
		CON_TIMEOUT				= new symbiosis_response_code(43, "CON_TIMEOUT", "Timeout elapsed before transaction completion", CONNECTIVITY);


	public static List<symbiosis_response_code> getAllResponseCodes() {
		List<symbiosis_response_code> responseCodes = new ArrayList<>();
		for (Field field : SymbiosisConfig.class.getDeclaredFields()) {
			if (field.getType().equals(symbiosis_response_code.class)) {
				try { responseCodes.add((symbiosis_response_code)field.get(field.getName())); }
				catch (IllegalAccessException e) { e.printStackTrace(); }
			}
		}
		return responseCodes;
	}
}
