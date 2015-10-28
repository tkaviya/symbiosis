package net.blaklizt.symbiosis.sym_common.utilities;

import java.nio.file.FileSystems;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

import static net.blaklizt.symbiosis.sym_common.utilities.Validator.isNullOrEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 8/12/13
 * Time: 10:05 PM
 */
public class CommonUtilities
{
    public static String joinWithDelimiter(Object delimiter, final Object... args) {

        if (args == null || Arrays.asList(args).stream().filter(Objects::nonNull).count() == 0) { return null; }

        if (delimiter == null) { delimiter = ""; }

        StringBuilder stringBuilder = new StringBuilder();

        for (Object arg : args) {
			if (arg != null) {
                if (!arg.toString().trim().equals("")) {
                    stringBuilder.append(arg).append(delimiter);
                }
            }
		}
        if (!delimiter.equals("") && stringBuilder.toString().endsWith(delimiter.toString())) {
			return stringBuilder.substring(0, stringBuilder.lastIndexOf(delimiter.toString()));
		} else {
			return stringBuilder.toString();
		}
	}

    public static String join(final Object... parts) { return joinWithDelimiter(null, parts); }

	public static String toCamelCase(final String str) { return toCamelCase(str, " "); }

	public static String toCamelCase(final String str, final String delimiter) {

		if (isNullOrEmpty(str)) return str;

		StringBuilder returnString = new StringBuilder(str.length());

		for (final String word : str.split(delimiter)) {
			if (!word.isEmpty()) {
				returnString.append(word.substring(0, 1).toUpperCase());
				returnString.append(word.substring(1).toLowerCase());
			}
			if (!(returnString.length() == str.length()))
				returnString.append(delimiter);
		}

		return returnString.toString();
	}

    public static String deCapitalize(final String str) {
        return isNullOrEmpty(str) ? str : join(str.substring(0, 1).toLowerCase(), str.substring(1));
    }
    
    public static String capitalize(final String str) { 
        return isNullOrEmpty(str) ? str : Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    
	public static String alignStringToLength(String str, final int length) {
        if (isNullOrEmpty(str)) str = "";
		while (str.length() < length) { str += " "; }
		return str;
	}

	public static String formatDoubleToMoney(final double value, final String currencySymbol)
	{
		DecimalFormat df = new DecimalFormat("###,##0.00");

		String formattedString = df.format(value);

		if (!formattedString.startsWith("-"))
			return currencySymbol + formattedString;
		else
			return "-" + currencySymbol + formattedString.replaceFirst("-", "");
	}

    public static String tempFolderLocation() {
        String fileSystemSeparatorChar = FileSystems.getDefault().getSeparator();
        String tempFileDirectory = System.getProperty("java.io.tmpdir");
        return tempFileDirectory.endsWith(fileSystemSeparatorChar) ? tempFileDirectory : tempFileDirectory + fileSystemSeparatorChar;
    }

	public static int round(final double d)
	{
		double dAbs = Math.abs(d);
		int i = (int) dAbs;

		if((dAbs - (double)i) < 0.5)	return d < 0 ? -i		: i;		//negative value
		else							return d < 0 ? -(i+1)	: i + 1;	//positive value
	}
}
