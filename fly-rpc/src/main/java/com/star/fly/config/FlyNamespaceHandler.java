package com.star.fly.config;

import com.star.fly.refer.ReferenceBean;
import com.star.fly.service.ServiceBean;
import com.star.fly.zookeeper.ZookeeperRegister;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by  wuyunxing on   2016/7/28.
 */

public class FlyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("service",new FlyDefinitionParser(ServiceBean.class));
        registerBeanDefinitionParser("consumer",new FlyDefinitionParser(ReferenceBean.class));
        registerBeanDefinitionParser("registry",new FlyDefinitionParser(ZookeeperRegister.class));
    }
}
