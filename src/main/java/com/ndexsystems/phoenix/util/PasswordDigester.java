package com.ndexsystems.phoenix.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordDigester
{
	private static final int PW_ITERATION_NUMBER = 10000;
	private static final int KEY_LENGTH = 24*8;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

	
	/**
	 * From a password, a number of iterations and a salt, returns the corresponding digest
	 * 
	 * @param iterationNb
	 *            int The number of iterations of the algorithm
	 * @param password
	 *            String The password to encrypt
	 * @param salt
	 *            byte[] The salt
	 * @return byte[] The digested password
	 * @throws NoSuchAlgorithmException
	 *             If the algorithm doesn't exist
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] getHash(String password, byte[] salt)
	{
		byte[] input = null;

		try
		{
			  KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PW_ITERATION_NUMBER, KEY_LENGTH);
	
			  SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
	
			  input =  f.generateSecret(spec).getEncoded();
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new FatalError(e);
		}
		catch (InvalidKeySpecException e1)
		{
			throw new FatalError(e1);
		}
		return input;
	}

	public static byte[] getNewSalt()
	{
		byte[] bSalt = null;
		
		try
		{
	     // Uses a secure Random not a simple Random
	     SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	     // Salt generation 128 bits long as recommended by NIST
	     bSalt = new byte[16];
	     random.nextBytes(bSalt);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new FatalError(e);
		}
		return bSalt;
	}

}
