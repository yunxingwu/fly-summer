package com.star.fly;

import com.star.fly.service.InvokerHanlder;

import java.lang.reflect.Proxy;

/**
 * Created by  wuyunxing on   2016/7/21.
 */

public class ProxyFactory {
    public static <T> T getProxy(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T proxy = (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),tClass.getInterfaces(),new InvokerHanlder(tClass));
        return proxy;
    }

}
