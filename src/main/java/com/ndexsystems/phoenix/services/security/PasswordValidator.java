package com.ndexsystems.phoenix.services.security;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.ndexsystems.phoenix.util.PasswordDigester;

@Component
public class PasswordValidator {

    public boolean validPassword(String password, String dbDigest, String dbSalt) {
        try {
            if (dbSalt != null) {
                byte[] bDigest = base64ToBytes(dbDigest);
                byte[] bSalt = base64ToBytes(dbSalt);

                byte[] proposedDigest = PasswordDigester.getHash(password, bSalt);
                return Arrays.equals(proposedDigest, bDigest);
            } else {
                return digestPassword(password).equals(dbDigest);
            }
        } catch (Exception e) {
            return false;
        }
    }

    private byte[] base64ToBytes(String value) {
        return Base64.getDecoder().decode(value);
    }

    private String digestPassword(String password) throws Exception {
        byte[] digest = MessageDigest.getInstance("MD5").digest(password.getBytes());

        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            int next = b & 0xFF; // convert signed -> unsigned
            if (next < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(next));
        }

        return sb.toString();
    }
}
