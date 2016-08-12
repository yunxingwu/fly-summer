package com.star.fly;

/**
 * Created by  wuyunxing on   2016/7/28.
 */

public class TestBean<T> {
    public TestBean(){
        System.out.println("init test bean");
    }
    private String name;
    private T ref;
    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
        System.out.println("ref is:"+ref.getClass().getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set name:"+name);
    }
}
