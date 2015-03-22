package net.blaklizt.symbiosis.sym_common.utilities;

import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
import org.apache.log4j.Logger;

import java.io.*;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 7/20/13
 * Time: 8:33 PM
 */
public class Format {

	private static final Logger logger = Configuration.getNewLogger(Format.class.getSimpleName());

	public static enum HTML_COLOR
	{
		BLACK	("#000000"),
		WHITE	("#FFFFFF"),
		RED		("#FF0000"),
		GREEN	("#00FF00"),
		BLUE	("#0000FF"),
		ORANGE	("#FFA500");

		private String color;

		HTML_COLOR(String color)
		{
			this.color = color;
		}

		public String getColor()
		{
			return this.color;
		}
	}

	public static String formatColor(String text, String color)
	{
		if (text.contains("<span style=\'"))
			return text.replaceFirst("<span style=\'", "<span style=\'color: \" + color + \"; ");
		else
			return "<span style=\'color: " + color + ";\'>" + text + "</span>";
	}

	public static String formatBlink(String text)
	{
		return "<emphasize>" + text + "</emphasize>";
	}

	/** Save the object into a Base64 string. */
	public static String objectToBase64(Serializable object)
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream( baos );
			oos.writeObject(object);
			oos.close();
			return new String(encodeBase64(baos.toByteArray()), "UTF-8");
		}
		catch (Exception ex)
		{
			logger.error("Failed to serialize object: " + ex.getMessage());
			return null;
		}
	}

	/** Read the object from Base64 string. */
	public static Object objectFromBase64(String encodedString)
	{
		try
		{
			byte [] data = decodeBase64(encodedString);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
			Object o  = ois.readObject();
			ois.close();
			return o;
		}
		catch (Exception ex)
		{
			logger.error("Failed to serialize object: " + ex.getMessage());
			return null;
		}
	}

	public static String bytesToHexString(byte[] bytes)
	{
		//convert the byte to hex format
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
