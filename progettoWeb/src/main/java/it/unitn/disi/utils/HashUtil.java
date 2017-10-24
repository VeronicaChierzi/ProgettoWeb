/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author root
 */
public class HashUtil {

    public static String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes(Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateSecureHash(String s) {
        return MD5(s + generateIV());
    }

    public static String generateIV() {
        return "" + ThreadLocalRandom.current().nextInt(0, 9000000 + 1);
    }

    public static String generatePasswordHash(String psw) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(psw.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
