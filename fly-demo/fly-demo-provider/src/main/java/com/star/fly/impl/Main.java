package com.star.fly.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by  wuyunxing on   2016/8/4.
 */

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application.xml");
        context.start();
    }
}
