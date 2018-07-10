package com.example.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author lishunli
 * @create 2017-11-16 16:06
 **/
public class HttpUtil {
    private static HttpUtil instance = null;
   private static  CloseableHttpClient httpclient = HttpClients.createDefault();

    private HttpUtil() {
    }

    public static HttpUtil instatic() {

        return instance = new HttpUtil();
    }

    public  String senfGet(String url){
        String result="";
        CloseableHttpResponse response=null;
        try {
            HttpGet get=new HttpGet(url);
             response= httpclient.execute(get);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
