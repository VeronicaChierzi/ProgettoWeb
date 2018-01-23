/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.utils;

/**
 *
 * @author Luca Degani
 */
public class UrlUtils {

    public static String cambiaUrl(String url, String param, String val) {
        if (url.contains(param)) {
            int startOld = url.indexOf(param + "=") + (param + "=").length();
            String sub = url.substring(startOld);
            if (sub.contains("&")) {
                int i = sub.indexOf("&");
                url = url.substring(0, startOld) + val + url.substring(startOld + i);
            } else {
                url = url.substring(0, startOld) + val;
            }
        } else {
            //se contiene ? oppure no lo aggiungo infondo
            if (url.contains("?")) {
                url += "&" + param + "=" + val;
            } else {
                url += "?" + param + "=" + val;
            }
        }
        return url;
    }

    public static int getOffsetFromUrl(String url) {
        if(url.contains("offset")) {
            int startOld = url.indexOf("offset=") + ("offset=").length();
            String sub = url.substring(startOld);
            if (sub.contains("&")) {
                int i = sub.indexOf("&");
                return Integer.parseInt(url.substring(startOld, startOld+i));
            } else {
                return Integer.parseInt(url.substring(startOld));
            }
        } else {
            return 0;
        }
    }

}
