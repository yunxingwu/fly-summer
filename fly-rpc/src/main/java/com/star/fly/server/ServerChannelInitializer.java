package com.star.fly.server;

import com.star.fly.coder.RpcDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 *  初始化网络时间处理Channel
 * Created by  wuyunxing on   2016/7/19.
 */

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new RpcDecoder())
                                                     .addLast(new ServerHandler());
    }
}
