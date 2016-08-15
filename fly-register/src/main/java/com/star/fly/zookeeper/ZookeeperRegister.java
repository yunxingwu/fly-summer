package com.star.fly.zookeeper;

import com.star.fly.Register;
import com.star.fly.url.URL;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Created by  wuyunxing on   2016/7/12.
 */

public class ZookeeperRegister extends AbstractClient implements Register {
    private static ZkClient zkClient;
    private static String address;
    private static String root = "/fly";
    private static ZookeeperRegister INSTANCE ;

    public ZookeeperRegister(){
        INSTANCE = this;
    }
    public static ZookeeperRegister getRegister(){
        if (INSTANCE==null ){
            System.out.println("ZookeeperRegister  is null");
        }
        zkClient = new ZkClient(address);
        return     INSTANCE ;
    }
    public void register(URL url) {
        create(urlToPath(url),url,true);
    }

    public void unregister(URL url) {
        zkClient.delete(urlToPath(url));
    }

    protected String urlToPath(URL url){
        String path = this.root+"/"+url.getServiceName()+"/providers/"+url.urlEncoder(url.toString());
        System.out.println("path:"+path);
        return path;
    }

    protected void create(String path,URL url,boolean ephemeral){
        int i = path.lastIndexOf("/");
        if (i>0){
            create(path.substring(0,i),url,false);
        }
        if (!zkClient.exists(path)){
        if (ephemeral) {
            zkClient.createEphemeral(path,url.toString());
        }else {
            zkClient.createPersistent(path,url.toString());
        }}
    }

    public void getServices(){
        List<String> services =  zkClient.getChildren(root);
        for(String service:services){
            System.out.println(service.toString());
        }
    }

    public String getService(String serviceName){
        List<String> services =  zkClient.getChildren(root);
        for (String service:services){
            if (service.endsWith(serviceName))return service;
        }
        return null;
    }

    public void subscribe(String serviceName) throws InterruptedException {
        String service = getService(serviceName);
        String path = root+"/"+service+"/providers";
        urls  = zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("node is change:"+s+"  "+list.toString());
                urls = list;
                System.out.println("urls change size:"+urls.size()+urls.toString());
            }
        });
        System.out.println("urls size:"+urls.size()+urls.toString());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void main(String[] args) {
        String a = "/dfdfd";
        System.out.println(a.lastIndexOf("/"));
        System.out.println(a.substring(0,a.lastIndexOf("/")));
    }
}
