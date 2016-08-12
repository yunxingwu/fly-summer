package com.star.fly.coder;
import java.io.*;

/**
 * Created by  wuyunxing on   2016/7/14.
 */

public class SerializaUtil {
    public static byte[] objectTobyte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        System.out.println("bytes size:"+bytes.length);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}

