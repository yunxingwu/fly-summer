package com.star.fly.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by  wuyunxing on   2016/7/13.
 */

public class NetUtil {
    //获取本机地址
    public static String getHostAddress() {
        String address = null;
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address;
    }
    public static void main(String[] args) throws UnknownHostException {
        InetAddress a = InetAddress.getLocalHost();
        System.out.println(a.getHostAddress());
        System.out.println(a.getHostName());
    }
}
