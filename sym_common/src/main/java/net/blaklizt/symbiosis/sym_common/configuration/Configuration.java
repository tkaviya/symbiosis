package net.blaklizt.symbiosis.sym_common.configuration;

import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 2013/07/06
 * Time: 11:21 AM
 */


public class Configuration
{
    /* configuration core settings */
    protected static final Logger log4j = Logger.getLogger(Configuration.class);
    protected static Configuration configuration = null;

	public static Configuration getInstance()
    {
        if (configuration == null)
        {
            configuration = new Configuration();
        }
        return configuration;
    }

	public static Logger getNewLogger(String loggerName)
	{
		return Logger.getLogger(loggerName);
	}

	public static String getProperty(String property)
	{
		//return getProperty("symbiosis", property);
		return ResourceBundle.getBundle("properties/symbiosis", Locale.ENGLISH).getString(property);
	}

	public static String getProperty(String bundle, String property)
	{
		return ResourceBundle.getBundle("properties/" + bundle, Locale.ENGLISH).getString(property);
	}
}
