package net.blaklizt.symbiosis.sym_common.enumeration;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public enum ResponseCode
{
	/*
	 * Response codes below 0 should never be show to user, should show general error
	 */
	SUCCESS
	{
		public int responseCode() {return 0;}
		public String responseMsg() {return "Successful";}
	},

	//system errors
	GENERAL_ERROR
	{
		public int responseCode() {return 1;}
		public String responseMsg() {return "A general error occurred";}
	},
	CONFIGURATION_INVALID
	{
		public int responseCode() {return -1;}
		public String responseMsg() {return "Specified configuration is not valid";}
	},

	//input errors
	INCOMPLETE_REQUEST
	{
		public int responseCode() {return 2;}
		public String responseMsg() {return "Incomplete request specified";}
	},
	INVALID_REQUEST
	{
		public int responseCode() {return 3;}
		public String responseMsg() {return "Invalid request specified";}
	},
	INVALID_EMAIL
	{
		public int responseCode() {return 4;}
		public String responseMsg() {return "Email provided was not valid";}
	},
	INVALID_MSISDN
	{
		public int responseCode() {return 5;}
		public String responseMsg() {return "Phone number provided was not valid";}
	},
	INVALID_FIRST_NAME
	{
		public int responseCode() {return 6;}
		public String responseMsg() {return "First name provided was not valid";}
	},
	INVALID_LAST_NAME
	{
		public int responseCode() {return 7;}
		public String responseMsg() {return "Last name provided was not valid";}
	},
	INVALID_USERNAME
	{
		public int responseCode() {return 8;}
		public String responseMsg() {return "Username provided was not valid";}
	},
	INVALID_PASSWORD
	{
		public int responseCode() {return 9;}
		public String responseMsg() {return "Password provided was not valid";}
	},
	INVALID_NAME
	{
		public int responseCode() {return 9;}
		public String responseMsg() {return "Name provided was not valid";}
	},



	INSUFFICIENT_PRIVILEGES
	{
		public int responseCode() {return 20;}
		public String responseMsg() {return "Insuffiecient privileges for current operation";}
	},
	AUTHENTICATION_FAILED
	{
		public int responseCode() {return 21;}
		public String responseMsg() {return "Authentication failed";}
	},

	//Account Status
	ACTIVE
	{
		public int responseCode() {return 30;}
		public String responseMsg() {return "Account is active";}
	},
	INACTIVE
	{
		public int responseCode() {return 31;}
		public String responseMsg() {return "Account is inactive";}
	},
	SUSPENDED
	{
		public int responseCode() {return 32;}
		public String responseMsg() {return "Account has been suspended";}
	},
	CLOSED
	{
		public int responseCode() {return 33;}
		public String responseMsg() {return "Account has been closed";}
	},
	PASSWORD_TRIES_EXCEEDED
	{
		public int responseCode() {return 34;}
		public String responseMsg() {return "Password tries exceeded";}
	},
	INCORRECT_PASSWORD
	{
		public int responseCode() {return 35;}
		public String responseMsg() {return "Password is incorrect";}
	},
	PASSWORD_EXPIRED
	{
		public int responseCode() {return 36;}
		public String responseMsg() {return "Password expired";}
	},
	NON_EXISTANT
	{
		public int responseCode() {return 37;}
		public String responseMsg() {return "User does not exist";}
	},

	//Connectivity codes
	CONNECTION_FAILED
	{
		public int responseCode() {return 40;}
		public String responseMsg() {return "Connection failed";}
	},
	UNKNOWN_HOST
	{
		public int responseCode() {return 41;}
		public String responseMsg() {return "Unknown host";}
	},
	CONNECTION_REFUSED
	{
		public int responseCode() {return 42;}
		public String responseMsg() {return "Connection Refused";}
	},
	TIMEOUT
	{
		public int responseCode() {return 43;}
		public String responseMsg() {return "Timeout elapsed before transaction completion";}
	},

	// Registration Codes
	REGISTRATION_FAILED
	{
		public int responseCode() {return 351;}
		public String responseMsg() {return "Registration Failed";}
	},
	PREVIOUS_MSISDN_FOUND
	{
		public int responseCode() {return 352;}
		public String responseMsg() {return "Mobile number has been previously registered";}
	},
	PREVIOUS_EMAIL_FOUND
	{
		public int responseCode() {return 353;}
		public String responseMsg() {return "Email has been previously registered";}
	},
	PREVIOUS_REGISTRATION_FOUND
	{
		public int responseCode() {return 354;}
		public String responseMsg() {return "Previous registration found.";}
	}
	;
	public abstract int responseCode();
	public abstract String responseMsg();

	static Map<Integer, ResponseCode> enumMap;

	public static ResponseCode valueOf(int value)
	{
		if(enumMap == null)
		{
			enumMap = new HashMap<>();
			for(ResponseCode rc : ResponseCode.values())
			{
				enumMap.put(rc.responseCode(), rc);
			}
		}
		return enumMap.get(value);
	}

	public String toJSONResponse()
	{
		JSONObject responseJSON = new JSONObject();
		try
		{
			responseJSON.put("response_message", this.responseMsg());
			responseJSON.put("response_code", this.responseCode());
			return responseJSON.toString();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}