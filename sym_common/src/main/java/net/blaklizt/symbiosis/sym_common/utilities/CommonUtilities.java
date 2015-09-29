package net.blaklizt.symbiosis.sym_common.utilities;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/12/13
 * Time: 10:05 PM
 */
public class CommonUtilities
{
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
