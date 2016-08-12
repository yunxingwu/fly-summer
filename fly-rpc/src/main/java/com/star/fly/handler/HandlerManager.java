package com.star.fly.handler;

import com.star.fly.coder.RpcEncoder;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  wuyunxing on   2016/7/21.
 */

public class HandlerManager  extends ChannelInitializer<SocketChannel> {
    private List<ChannelHandlerAdapter> handlers = new ArrayList<>();
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline =  socketChannel.pipeline();
        pipeline.addLast(new RpcEncoder());
        for (ChannelHandlerAdapter handler:handlers){
            pipeline.addLast(handler);
        }
    }

    public void addHandler(ChannelHandlerAdapter handlerAdapter){
        handlers.add(handlerAdapter);
    }
}
