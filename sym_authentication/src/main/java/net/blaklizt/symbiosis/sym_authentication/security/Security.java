package net.blaklizt.symbiosis.sym_authentication.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 12/8/13
 * Time: 7:05 PM
 */
public class Security
{
	public static String generateIV()
	{
		int n = 16;
		char[] iv = new char[n];
		int c = 'A', r1;

		for (int i = 0; i < n; i++)
		{
			r1 = (int) (Math.random() * 3);
			switch (r1)
			{
				case 0:	c = '0' + (int) (Math.random() * 10);	break;
				case 1:	c = 'a' + (int) (Math.random() * 26);	break;
				case 2:	c = 'A' + (int) (Math.random() * 26);	break;
			}
			iv[i] = (char) c;
		}
		return new String(iv);
	}

	public static String encrypt(byte [] input)
	{
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();

			byte[] digested = messageDigest.digest(input);
			StringBuffer sb = new StringBuffer();

			for (byte aDigested : digested) { sb.append(Integer.toHexString(0xff & aDigested)); }

			return sb.toString();
		}
		catch (NoSuchAlgorithmException ex) { ex.printStackTrace(); }

		return null;
	}
}
