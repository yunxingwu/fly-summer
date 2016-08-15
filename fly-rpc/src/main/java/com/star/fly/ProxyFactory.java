package com.star.fly;

import com.star.fly.service.InvokerHanlder;

import java.lang.reflect.Proxy;

/**
 *  代理工厂类，负责创建代理对象
 * Created by  wuyunxing on   2016/7/21.
 */

public class ProxyFactory {
    public static <T> T getProxy(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T proxy = (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),tClass.getInterfaces(),new InvokerHanlder(tClass));
        return proxy;
    }

}
