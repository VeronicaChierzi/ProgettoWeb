/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.utils;

/**
 *
 * @author root
 */
public class StringUtils {

    public static String formatForWeb(String s) {
        if (s != null) {
            String ret = "";
            if (s.contains("&")) {
                boolean modifica = true;                
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '&') {
                        modifica = false;
                        ret += s.charAt(i);
                    } else if (s.charAt(i) == ';') {
                        modifica = true;
                        ret += s.charAt(i);
                    } else if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' && modifica) {
                        ret += (s.charAt(i) + "").toUpperCase();
                    } else {
                        ret += s.charAt(i);
                    }
                }
            } else {
                return s.toUpperCase();
            }

            return ret;
        }
        return "";
    }

}
