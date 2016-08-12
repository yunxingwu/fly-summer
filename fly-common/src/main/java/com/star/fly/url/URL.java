package com.star.fly.url;

import com.star.fly.net.NetUtil;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created by  wuyunxing on   2016/7/12.
 */

public class URL  implements Serializable{
    private static final long serialVersionUID = 1L;
    private  final String host;
    private final String serviceName;
    private final String[] methods;
    private final int port;
    protected URL(){
        this.host = null;
        this.serviceName = null;
        this.methods = null;
        this.port = 0;
    }
    public URL(String host,String serviceName,String[] method,int port){
       this.host = host;
        this.serviceName = serviceName;
        this.methods = method;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String[] getMethod() {
        return methods;
    }



    public int getPort() {
        return port;
    }

    public String toString(){
        String url =  NetUtil.getHostAddress()+":"+port+"/"+serviceName+"&method="+ Arrays.toString(methods);
        return url;
    }

    public static String urlEncoder(String url){
        try {
             return URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlDecoder(String url){
        try {
            return URLDecoder.decode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String c = b+2;
        String d = "hello";
        String e = d+2;
        System.out.println(a==c);
        System.out.println(a==e);
    }
}
