/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class CoordinateUtil {

    public static float[] addressToCoordinates(String addr) {

        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        url += addr.replace(" ", "+");
        url += "&key=AIzaSyCrBfS6gq4DRjdnLI8fmMUQNvKEIp8hegg";
        
        
        float[] coo = new float[2];

        

        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CoordinateUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CoordinateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JsonObject jobj = new Gson().fromJson(jsonText, JsonObject.class);

            JsonObject location = jobj.get("results").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("location").getAsJsonObject();
            float lat = location.get("lat").getAsFloat();
            float lng = location.get("lng").getAsFloat();
            
            coo[0] = lat;
            coo[1] = lng;

        } catch (IOException ex) {
            Logger.getLogger(CoordinateUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(CoordinateUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return coo;

    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
