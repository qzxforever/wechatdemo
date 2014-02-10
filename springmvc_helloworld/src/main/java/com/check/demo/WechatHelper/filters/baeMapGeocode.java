package com.check.demo.WechatHelper.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class baeMapGeocode {
  private static String ak = "5ef2641d89438a6e708db122820cf1d2";
    
    public static Map<String, String> testPost(String x, String y) throws IOException {
        URL url = new URL("http://api.map.baidu.com/geocoder?" + ak + "=�����Կ" + 
        		"&callback=renderReverse&location=" + x
                        + "," + y + "&output=json");
        URLConnection connection = url.openConnection();
        
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream(), "utf-8");
//        remember to clean up
        out.flush();
        out.close();
//        һ�����ͳɹ��������·����Ϳ��Եõ��������Ļ�Ӧ��
        String res;
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                l_urlStream,"UTF-8"));
        StringBuilder sb = new StringBuilder("");
        while ((res = in.readLine()) != null) {
            sb.append(res.trim());
        }
        String str = sb.toString();
        //System.out.println(str);
        Map<String,String> map = null;
    if(StringUtils.isNotEmpty(str)) {
      int addStart = str.indexOf("formatted_address\":");
      int addEnd = str.indexOf("\",\"business");
      if(addStart > 0 && addEnd > 0) {
        String address = str.substring(addStart+20, addEnd);
        map = new HashMap<String,String>();
        map.put("address", address);
        return map;		
      }
    }
    
    return null;
    
    }
  
  public static void main(String[] args) throws IOException {
    	Map<String, String> json = baeMapGeocode.testPost("29.542938", "114.064022");
        System.out.println("address :" + json.get("address"));
    }
}