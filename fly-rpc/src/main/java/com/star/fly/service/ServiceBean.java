package com.star.fly.service;

import com.star.fly.ProxyFactory;
import com.star.fly.net.NetUtil;
import com.star.fly.server.RpcServer;
import com.star.fly.url.URL;
import com.star.fly.zookeeper.ZookeeperRegister;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by  wuyunxing on   2016/7/12.
 */

public class ServiceBean<T> implements ApplicationListener {
    //接口服务名
    private String interfaceClass;
    //接口服务实现类
    private T ref;
    public static final Map<String,Object> exporterMap = new ConcurrentHashMap<String, Object>();
//    private ZookeeperRegister register = new ZookeeperRegister();

    public void export() throws IllegalAccessException, InstantiationException, UnknownHostException, InterruptedException {
        System.out.println("do export.....");
        try {
            Class clz = Class.forName(interfaceClass);
            Method[] methods = clz.getDeclaredMethods();
            ArrayList<String> methodNames = new ArrayList<String>();
            for (Method m:methods){
                methodNames.add(m.getName());
            }
            String [] methodArray =  methodNames.toArray(new String[1]);
            URL url = new URL(NetUtil.getHostAddress(),interfaceClass,methodArray,2018);
            Object proxy =  ProxyFactory.getProxy(ref.getClass());     //创建实现类代理
            String key = URL.urlEncoder(url.toString());
            exporterMap.put(key,proxy);
            //get注册中心
           ZookeeperRegister register = ZookeeperRegister.getRegister();
            register.register(url);            //注册服务URL
            RpcServer rpcServer = new RpcServer();
            rpcServer.start();            //启动进程绑定端口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try {
            if (ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())){
                export();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }
}
