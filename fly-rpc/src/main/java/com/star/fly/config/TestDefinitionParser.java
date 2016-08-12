package com.star.fly.config;

import com.star.fly.TestBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * Created by  wuyunxing on   2016/7/27.
 */

public class TestDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(TestBean.class);
        rootBeanDefinition.getPropertyValues().addPropertyValue("name",name);

        String reff = element.getAttribute("ref");
        System.out.println(reff);
        if(parserContext.getRegistry().containsBeanDefinition("ccc")){
            System.out.println("if");
            BeanDefinition refbean = parserContext.getRegistry().getBeanDefinition("ccc");
            rootBeanDefinition.getPropertyValues().add("ref",refbean);
        }else {
            System.out.println("else");
            Object ref = new RuntimeBeanReference("ccc");
            System.out.println(ref.getClass().getName());
            rootBeanDefinition.getPropertyValues().add("ref",ref);
        }
        parserContext.getRegistry().registerBeanDefinition(id,rootBeanDefinition);

        return rootBeanDefinition;
    }
}
