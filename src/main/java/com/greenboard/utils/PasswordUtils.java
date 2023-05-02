package com.greenboard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    private static final int SALT_LENGTH = 32;
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    public static boolean checkPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        String[] parts = hashedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);
        byte[] testHash = hashPassword(password, salt);
        return MessageDigest.isEqual(hash, testHash);
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    private static byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(password.getBytes());
        for (int i = 0; i < ITERATIONS; i++) {
            digest.reset();
            hash = digest.digest(hash);
        }
        return hash;
    }

}
