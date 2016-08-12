package com.star.fly.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by  wuyunxing on   2016/7/27.
 */

public class TestNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("test",new TestDefinitionParser());
    }
}
