package com.star.fly.handler;

import com.star.fly.RpcRequest;
import com.star.fly.net.NetUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by  wuyunxing on   2016/7/14.
 */

public class RpcClient {

    private HandlerManager manager;

    public RpcClient(HandlerManager manager) {
        this.manager = manager;
    }

    public void connect(String address,int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(manager);
            ChannelFuture future = b.connect(address,port).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        RpcRequest request = new RpcRequest("com.star.perfect.netty.server.Hello","hello",null,null);
//        HandlerManager manager = new HandlerManager();
//        manager.addHandler(new RpcHandler(request));
//        new RpcClient(manager).connect();
    }
}

