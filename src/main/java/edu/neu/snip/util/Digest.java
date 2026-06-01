package edu.neu.snip.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
    private static final String MD5 = "MD5";

    public static String digest(String originalUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            byte[] bytes = md.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
