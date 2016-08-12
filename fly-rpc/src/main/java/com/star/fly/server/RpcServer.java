package com.star.fly.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 *  rpc服务端
 * Created by  wuyunxing on   2016/7/19.
 */

public class RpcServer {
    private static final int DEFAULT_PORT= 2018;
    public void start() throws InterruptedException, UnknownHostException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动辅助类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap = bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ServerChannelInitializer());
            // 服务器启动后 绑定监听端口 同步等待成功 主要用于异步操作的通知回调 回调处理用的ServerHandler
            ChannelFuture f = bootstrap.bind(DEFAULT_PORT).sync();
            System.out.println("server start , ip is:"+ InetAddress.getLocalHost().getHostAddress());
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
