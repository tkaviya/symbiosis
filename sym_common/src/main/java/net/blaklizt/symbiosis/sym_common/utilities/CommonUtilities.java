package net.blaklizt.symbiosis.sym_common.utilities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/12/13
 * Time: 10:05 PM
 */
public class CommonUtilities
{
	public static boolean isNullOrEmpty(String string)
	{
		return string == null || string.trim().equals("");
	}

	public static boolean isValidEmail(String emailAddress)
	{
		return !isNullOrEmpty(emailAddress) && emailAddress.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
	}

    public static boolean isNumeric(Object testObject) { return testObject != null && Number.class.isAssignableFrom(testObject.getClass()); }

	public static void RefreshBundles()
	{
		ResourceBundle.clearCache();
	}

	public static Properties resourceBundleToProperties(ResourceBundle bundle)
	{
		Properties props = new Properties();
		Enumeration<String> keys = bundle.getKeys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			props.put(key, bundle.getObject(key));
		}
		return props;
	}

	public static String toCamelCase(String initialString) {

		if (initialString == null) return null;

		StringBuilder returnString = new StringBuilder(initialString.length());

		for (String word : initialString.split(" ")) {
			if (!word.isEmpty()) {
				returnString.append(word.substring(0, 1).toUpperCase());
				returnString.append(word.substring(1).toLowerCase());
			}
			if (!(returnString.length() == initialString.length()))
				returnString.append(" ");
		}

		return returnString.toString();
	}

	public static String alignStringToLength(String text, int length)
	{
		if (text == null) text = "";

		while (text.length() < length)
			text += " ";
		return text;
	}

	public static String getCurrencySymbol()
	{
		return getConfiguration("currencySymbol");
	}

	public static String getCountryCodePrefix()
	{
		return getConfiguration("countryCode");
	}

	public static String[] getConfigurations(String bundle,String property)
	{
		try
		{
			ResourceBundle rb = ResourceBundle.getBundle(bundle);
			if(rb.containsKey(property))
				return rb.getString(property).split("\\,");
			return null;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	public static String getConfiguration(String propertyKey)
	{
		ResourceBundle rb = ResourceBundle.getBundle("symbiosis");
		if(rb.containsKey(propertyKey))
			return rb.getString(propertyKey);
		return null;
	}

	public static String getConfiguration(String module, String propertyKey)
	{
		ResourceBundle rb = ResourceBundle.getBundle("properties/" + module + "_settings");
		if(rb.containsKey(propertyKey))
			return rb.getString(propertyKey);
		return null;
	}

	public static String getConfiguration(String bundle, String propertyKey, String defaultProperty)
	{
		try
		{
			String property = getConfiguration(bundle,propertyKey);
			if (property == null)
				return defaultProperty;
			else
				return property;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return defaultProperty;
		}
	}

	public static String formatDoubleToMoney(double value, String currencySymbol)
	{
		DecimalFormat df = new DecimalFormat("###,##0.00");

		String formattedString = df.format(value);

		if (!formattedString.startsWith("-"))
			return currencySymbol + formattedString;
		else
			return "-" + currencySymbol + formattedString.replaceFirst("-", "");
	}

	public static int round(double d)
	{
		double dAbs = Math.abs(d);
		int i = (int) dAbs;

		if((dAbs - (double)i) < 0.5)	return d < 0 ? -i		: i;		//negative value
		else							return d < 0 ? -(i+1)	: i + 1;	//positive value
	}
}
