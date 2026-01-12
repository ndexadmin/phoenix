package com.ndexsystems.phoenix.services.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.MessageDigest;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ndexsystems.phoenix.util.PasswordDigester;

public class PasswordValidatorTest {

	private PasswordValidator validator;

	@BeforeEach
	void init() {
		validator = new PasswordValidator();
	}

	private String md5Hex(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(password.getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				int v = b & 0xFF;
				if (v < 16)
					sb.append('0');
				sb.append(Integer.toHexString(v));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	private String pbkdf2Base64(String password, byte[] salt) {
		byte[] hash = PasswordDigester.getHash(password, salt);
		return Base64.getEncoder().encodeToString(hash);
	}

	@Test
	void test_validPassword_MD5_NoSalt() {
		String password = "test123";

		
		String expectedDigest = md5Hex(password);

		assertTrue(validator.validPassword(password, expectedDigest, null),
				"Legacy MD5 should validate correctly without salt");
	}

	@Test
	void test_invalidPassword_MD5_NoSalt() {
		String password = "goodPass";
		String wrongPassword = "badPass";

		String correctDigest = md5Hex(password);

		assertFalse(validator.validPassword(wrongPassword, correctDigest, null),
				"MD5 digest should not match wrong password");
	}

	@Test
	void test_validPassword_PBKDF2_WithSalt() {
		String password = "StrongPassword123";

		byte[] salt = PasswordDigester.getNewSalt();
		String digest = pbkdf2Base64(password, salt);

		String salt64 = Base64.getEncoder().encodeToString(salt);

		assertTrue(validator.validPassword(password, digest, salt64), "PBKDF2 password should validate successfully");
	}

	@Test
	void test_invalidPassword_PBKDF2_WithSalt() {
		String password = "CorrectPass";
		String wrongPass = "WrongPass";

		byte[] salt = PasswordDigester.getNewSalt();
		String digest = pbkdf2Base64(password, salt);

		String salt64 = Base64.getEncoder().encodeToString(salt);

		assertFalse(validator.validPassword(wrongPass, digest, salt64), "PBKDF2 should reject wrong password");
	}

	@Test
	void test_invalidDigest_PBKDF2() {
		String password = "abc123";

		byte[] salt = PasswordDigester.getNewSalt();
		String salt64 = Base64.getEncoder().encodeToString(salt);

		String corruptedDigest = "INVALID_DIGEST";

		assertFalse(validator.validPassword(password, corruptedDigest, salt64),
				"Invalid digest should cause validation to fail");
	}

	@Test
	void test_nullPassword() {
		assertFalse(validator.validPassword(null, "abc", null), "Null password must return false");
	}

	@Test
	void test_nullDigest() {
		assertFalse(validator.validPassword("test", null, null), "Null digest must return false");
	}
}
