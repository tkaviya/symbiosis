package net.blaklizt.symbiosis.sym_authentication.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import static net.blaklizt.symbiosis.sym_authentication.security.SymbiosisSecurityEncyption.*;
import static net.blaklizt.symbiosis.sym_common.utilities.Validator.isNullOrEmpty;

/**
 * Created with IntelliJ IDEA.
 * SymbiosisUser: tkaviya
 * Date: 12/8/13
 * Time: 7:05 PM
 */
public class Security
{
	private static SecureRandom secureRandom;

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

	public static byte[] generateSecureRandomBytes() {

		if (secureRandom == null) {
			secureRandom = new SecureRandom();
		}

		byte[] randomBytes = new byte[DEFAULT_SALT_LENGTH];

		secureRandom.nextBytes(randomBytes);

		return randomBytes;
	}

	public static String hashWithSalt(final String unencryptedStr, final byte[] salt) {
		return hashWithSalt(unencryptedStr, DEFAULT_SECURITY_HASH, salt);
	}

	public static String encrypt(final String input) {
		return hashWithSalt(input, DEFAULT_SECURITY_HASH, DEFAULT_ENCRYPTION_SALT);
	}

	public static String hashWithSalt(final String input, final String encryptMode, final byte[] salt)
	{
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(encryptMode);
			messageDigest.reset();

			if (!isNullOrEmpty(Arrays.toString(salt))) {
				messageDigest.update(salt);
			}

			byte[] digested = messageDigest.digest(input.getBytes());
			StringBuilder sb = new StringBuilder();

			for (byte aDigested : digested) {
				sb.append(Integer.toHexString(0xff & aDigested));
			}

			return sb.toString();
		}
		catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
