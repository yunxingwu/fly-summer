package com.star.fly.refer;


import com.star.fly.handler.ClientHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * Created by  wuyunxing on   2016/7/13.
 */

public class ReferenceBean<T>  implements InitializingBean ,FactoryBean{
    private String interfaceClass;
    private T ref;
    public T refer(){
        try {
            Class<?> clz = Class.forName(interfaceClass);
            ref = ClientHandler.getProxy(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ref;
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

    @Override
    public void afterPropertiesSet() throws Exception {
//        getObject();
    }

    @Override
    public Object getObject() throws Exception {
        return refer();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
