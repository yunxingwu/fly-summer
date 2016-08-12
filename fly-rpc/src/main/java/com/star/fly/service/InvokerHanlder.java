package com.star.fly.service;

import com.star.fly.Invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by  wuyunxing on   2016/7/20.
 */

public class InvokerHanlder<T>implements InvocationHandler{

    private Object object;

    public InvokerHanlder(Class<T> clz) throws IllegalAccessException, InstantiationException {
       object =  clz.newInstance();

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(object,args);
        return null;
    }
}
