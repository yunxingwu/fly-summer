package com.star.fly.server;


import com.star.fly.RpcRequest;
import com.star.fly.service.ServiceBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 *  网络事件处理类
 * Created by  wuyunxing on   2016/7/19.
 */

public class ServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("服务器读取到客户端请求...");
        System.out.println(msg.toString());
        RpcRequest request = (RpcRequest)msg;
        System.out.println(request.getKey());
        invoke(request);
        String curentTime="成功";
        ByteBuf resp= Unpooled.copiedBuffer(curentTime.getBytes());
        ctx.write(resp);
        System.out.println("服务器做出了响应");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("服务器readComplete 响应完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println("服务器异常退出："+cause.getMessage());
    }

    private void invoke(RpcRequest request) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o =  ServiceBean.exporterMap.get(request.getKey());
        Method method = o.getClass().getMethod(request.getMethodDesc(),request.getParameterTypes());
        System.out.println("调用方法："+method.getName());
        method.invoke(o,request.getArguments());
    }
}
