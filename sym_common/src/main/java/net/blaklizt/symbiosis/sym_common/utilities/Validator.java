package net.blaklizt.symbiosis.sym_common.utilities;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;

/**
 * User: tkaviya
 * Date: 3/27/2015
 * Time: 6:44 AM
 */
public class Validator
{
	private static final String MIN_PASSWORD_LENGTH = Configuration.getSymbiosisProperty("MinimumPasswordLength");

	private static final String MAX_PASSWORD_LENGTH = Configuration.getSymbiosisProperty("MaximumPasswordLength");

	public static boolean isNumeric(Object testObject) {
        if (testObject == null) return false;
        try { Float.parseFloat(String.valueOf(testObject)); return true; }
        catch (Exception e) { return false; }
    }

	public static boolean isValidEmail(String emailAddress)
	{
		return !isNullOrEmpty(emailAddress) && emailAddress.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
	}


	public static boolean isValidPassword(String password)
	{
		return password.matches("[a-zA-Z0-9<>.!@();:#$%&*+/=?^_{|}~-]{" + MIN_PASSWORD_LENGTH + "," + MAX_PASSWORD_LENGTH +"}");
	}

	public static boolean isValidMsisdn(String msisdn, String countryCodePrefix) {
        return isValidMsisdn(msisdn) || msisdn.matches("^(" + countryCodePrefix + ")[0-9]{9}");
	}

	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.trim().equals("");
	}

	public static boolean isValidMsisdn(String msisdn)          {   return msisdn.matches("^(0)[0-9]{9}");          }

	public static boolean isValidFirstName(String firstName)    {   return firstName.matches("[a-zA-Z]{2,50}");     }

	public static boolean isValidLastName(String lastName)      {   return isValidFirstName(lastName);              }

	public static boolean isValidUsername(String name)          {   return name.matches("[a-zA-Z0-9-_.]{6,50}");    }

}
