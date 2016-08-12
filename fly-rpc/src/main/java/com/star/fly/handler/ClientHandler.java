package com.star.fly.handler;

import com.star.fly.RpcRequest;
import com.star.fly.exception.NotService;
import com.star.fly.url.URL;

import com.star.fly.zookeeper.AbstractClient;
import com.star.fly.zookeeper.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * Created by  wuyunxing on   2016/7/21.
 */

public class ClientHandler extends AbstractClient implements InvocationHandler {
    private Object object;
    public ClientHandler(Object object){
        this.object = object;
    }

    public  static<T> T getProxy(Class object){
        return(T) Proxy.newProxyInstance(object.getClassLoader(), new Class[]{object}, new ClientHandler(object));
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws InterruptedException, NotService {
        RpcRequest request = new RpcRequest(method.getDeclaringClass().getName(),method.getName(),method.getParameterTypes(),args);
//        ZookeeperClient zkcli = new ZookeeperClient();
//        zkcli.subscribe(request.getClassName());
        ZookeeperRegister zkregister = ZookeeperRegister.getRegister();
        zkregister.subscribe(request.getClassName());
        String address = "";
        int port;
         //取随机数（随机访问一台可提供服务的地址）
        System.out.println("url size:"+urls.size());
        if (urls.size()>0){
            Random random = new Random();
            String url = urls.get(random.nextInt(urls.size()));
            String url1 = URL.urlDecoder(url);
            address = url1.substring(0,url1.indexOf(":"));
            port = Integer.parseInt(url1.substring(url1.indexOf(":")+1, url1.indexOf("/")));
            request.setKey(url);
        }else {
            throw new NotService("没有服务提供者");
        }
        HandlerManager manager = new HandlerManager();
        manager.addHandler(new RpcHandler(request));
        //开始远程调用
        new RpcClient(manager).connect(address,port);
        return null;
    }

    public void getAddressAndPort(String url){
        String url1 = URL.urlDecoder(url);
        int port = Integer.parseInt(url1.substring(url1.indexOf(":")+1, url1.indexOf("/")));
        System.out.println(port);
    }

}
