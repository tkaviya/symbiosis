package net.blaklizt.symbiosis.sym_common.configuration;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/07/06
 * Time: 11:21 AM
 */


public class Configuration
{
	private static final String DEFAULT_BUNDLE_NAME = "symbiosis";

	//get logger with settings from properties file
	public static Logger getNewLogger(String loggerName)
	{
		return Logger.getLogger(loggerName);
	}

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

	public static String getProperty(String bundle, String property)
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("properties/" + bundle, Locale.ENGLISH);
		if (resourceBundle != null)
			return resourceBundle.getString(property);
		return null;
	}

	public static String getProperty(String bundle, String propertyKey, String defaultProperty)
	{
		String property = getProperty(bundle, propertyKey);
		if (property == null)
			return defaultProperty;
		return property;
	}

	public static String getSymbiosisProperty(String property)
	{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("properties/" + DEFAULT_BUNDLE_NAME, Locale.ENGLISH);
		if (resourceBundle != null)
			return resourceBundle.getString(property);
		return null;
	}

//	public static String getOSProperty(String bundle, String property)
//	{
//		if (SystemUtils.IS_OS_LINUX)	property = "linux_" + property;
//		if (SystemUtils.IS_OS_WINDOWS)	property = "windows_" + property;
//		if (SystemUtils.IS_OS_MAC)		property = "mac_" + property;
//
//		return ResourceBundle.getBundle("properties/" + bundle, Locale.ENGLISH).getString(property);
//	}

	public static String getCurrencySymbol()
	{
		return getSymbiosisProperty("currencySymbol");
	}

	public static String getCountryCodePrefix()
	{
		return getSymbiosisProperty("countryCode");
	}

	public static String[] getProperties(String property) {
		return getProperties(DEFAULT_BUNDLE_NAME, property);
	}

	public static String[] getProperties(String bundle, String property) {
		String configurationData = getProperty(bundle, property);
		return configurationData != null ? configurationData.split(",") : null;
	}

}
