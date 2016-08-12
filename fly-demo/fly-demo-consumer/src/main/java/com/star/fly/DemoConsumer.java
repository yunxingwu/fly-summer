package com.star.fly;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by  wuyunxing on   2016/7/29.
 */

public class DemoConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
        Hello hello = (Hello)context.getBean("hello");
        hello.world();
    }
}
