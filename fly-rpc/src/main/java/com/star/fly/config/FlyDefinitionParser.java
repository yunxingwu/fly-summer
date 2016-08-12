package com.star.fly.config;

import com.star.fly.refer.ReferenceBean;
import com.star.fly.service.ServiceBean;
import com.star.fly.zookeeper.ZookeeperRegister;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by  wuyunxing on   2016/7/28.
 */

public class FlyDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;
    public FlyDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        if (ServiceBean.class.getName().equals(beanClass.getName())) {
            String interfaceName = element.getAttribute("interface");
            String ref = element.getAttribute("ref");
            Object object = new RuntimeBeanReference(ref);
            beanDefinition.getPropertyValues().add("ref", object);
            beanDefinition.getPropertyValues().add("interfaceClass", interfaceName);
            parserContext.getRegistry().registerBeanDefinition(interfaceName, beanDefinition);
        }else if(ReferenceBean.class.getName().equals(beanClass.getName())){
            String interfaceName = element.getAttribute("interface");
            beanDefinition.getPropertyValues().add("interfaceClass", interfaceName);
            String id = element.getAttribute("id");
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        }else if(ZookeeperRegister.class.getName().equals(beanClass.getName())){
            String address = element.getAttribute("address");
            beanDefinition.getPropertyValues().add("address",address);
            parserContext.getRegistry().registerBeanDefinition(beanClass.getName(), beanDefinition);
        }
        return beanDefinition;
    }
}
