package com.newsAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Zhan Shi
 */

public class news_web_service {

    public String requestData(String SingerName) {
    	
    	String data = "";
    	String httpUrl = "http://iris.lore.ai/api/v0.1/NEWS/?api_key=JfuQ9uhn4hRjmHJJr3PWFINNags&format=json&q=";
    	
        try {
           String sn_encoded = URLEncoder.encode(SingerName, "UTF-8");
           URL url = new URL(httpUrl + sn_encoded);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setRequestProperty("Accept", "application/json");
           System.out.println(conn.getResponseCode());

           if (conn.getResponseCode() != 200) {
                   throw new RuntimeException("Failed : HTTP error code : "
                                              + conn.getResponseCode());
           }

           BufferedReader br = new BufferedReader(new InputStreamReader(
                                                          (conn.getInputStream())));

            String str, line;
            str = "";
            while((line = br.readLine()) != null) {
            	str += line;
            }
            
            System.out.println(str);
            data = str;
            
        } catch (MalformedURLException e) {
            
            e.printStackTrace();
        
        } catch (IOException e) {
        
            e.printStackTrace();
        
        } 
        return data;
    }
}


