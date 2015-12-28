package net.blaklizt.symbiosis.sym_core_lib.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum SYM_RESPONSE_CODE
{
	/* Response codes below 0 should never be shown to user, should show general error instead */
	SUCCESS						(0,		"Successful"),

	//system errors
	GENERAL_ERROR				(1,	    "A general error occurred"),
	CONFIGURATION_INVALID		(-1,	"Specified configuration is not valid"),

	//input errors
	INCOMPLETE_REQUEST			(2,		"Incomplete request specified"),
	INVALID_REQUEST				(3,		"Invalid request specified"),
	INVALID_EMAIL				(4,	    "Email provided was not valid"),
	INVALID_MSISDN				(5,		"Phone number provided was not valid"),
	INVALID_FIRST_NAME			(6,		"First name provided was not valid"),
	INVALID_LAST_NAME			(7,		"Last name provided was not valid"),
	INVALID_USERNAME			(8,		"Username provided was not valid"),
	INVALID_PASSWORD			(9,		"Password provided was not valid"),
	INVALID_NAME				(10,	"Name provided was not valid"),

	INSUFFICIENT_PRIVILEGES		(20,	"Insuffiecient privileges for current operation"),
	AUTHENTICATION_FAILED		(21,	"Authentication failed"),

	//Account Status
	ACTIVE						(30,	"Account is active"),
	INACTIVE	                (31,	"Account is inactive"),
	SUSPENDED	                (32,	"Account has been suspended"),
	CLOSED	                    (33,	"Account has been closed"),
	PASSWORD_TRIES_EXCEEDED	    (34,	"Password tries exceeded"),
	INCORRECT_PASSWORD	        (35,	"Password is incorrect"),
	PASSWORD_EXPIRED	        (36,	"Password expired"),
	NON_EXISTANT	            (37,	"User does not exist"),

	//Connectivity codes
	CONNECTION_FAILED	        (40,	"Connection failed"),
	UNKNOWN_HOST	            (41,	"Unknown host"),
	CONNECTION_REFUSED	        (42,	"Connection Refused"),
	TIMEOUT	                    (43,	"Timeout elapsed before transaction completion"),

	// Registration Codes
	REGISTRATION_FAILED         (351,	"Registration Failed"),
	PREVIOUS_MSISDN_FOUND       (352,	"Mobile number has been previously registered"),
	PREVIOUS_EMAIL_FOUND        (353,	"Email has been previously registered"),
	PREVIOUS_REGISTRATION_FOUND (354,	"Previous registration found.")
	;

	public final int code;
	public final String message;

	SYM_RESPONSE_CODE(int code, String message) { this.code = code; this.message = message; }
	
	static Map<Integer, SYM_RESPONSE_CODE> enumMap;

	public static SYM_RESPONSE_CODE valueOf(int value) {

        if(enumMap == null) {

			enumMap = new HashMap<>();

			for(SYM_RESPONSE_CODE rc : SYM_RESPONSE_CODE.values()) { enumMap.put(rc.code, rc); }
		}
		return enumMap.get(value);
	}

//	public String toJSONResponse()
//	{
//		JSONObject responseJSON = new JSONObject();
//		try
//		{
//			responseJSON.put("response_message", this.responseMsg());
//			responseJSON.put("response_code", this.responseCode());
//			return responseJSON.toString();
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//			return null;
//		}
//	}
}