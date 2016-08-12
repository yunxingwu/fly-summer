package com.star.fly;

import com.star.fly.url.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by  wuyunxing on   2016/7/19.
 */

public class FlyExporter<T> implements Exporter {
    private  Object local;
    private URL url;
    public FlyExporter(URL url){
        this.url = url;
    }

    public Object getLocal() {
        return local;
    }

    public void setLocal(Object local) {
        this.local = local;
    }

    public void export() {
    }

    public void unexport() {
    }


}
