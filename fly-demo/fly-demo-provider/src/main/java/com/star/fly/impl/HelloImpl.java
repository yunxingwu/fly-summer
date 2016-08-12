package com.star.fly.impl;

import com.star.fly.Hello;

/**
 * Created by  wuyunxing on   2016/7/29.
 */

public class HelloImpl implements Hello {
    @Override
    public void hello(String name, int i) {
        System.out.println("hello "+name+" "+i);
    }

    @Override
    public void world() {
        System.out.println("hello world");
    }
}
