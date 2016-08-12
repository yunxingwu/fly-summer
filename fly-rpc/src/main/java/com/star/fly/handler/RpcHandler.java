package com.star.fly.handler;

import com.star.fly.RpcRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 *  网络IO事件处理类
 * Created by  wuyunxing on   2016/7/13.
 */

public class RpcHandler extends ChannelHandlerAdapter {
    private ByteBuf firstMessage;
    private RpcRequest rpcRequest;
    private Channel channel;
    public RpcHandler(RpcRequest rpcRequest){
        this.rpcRequest = rpcRequest;
    }
    /*
     * 此方法会在与服务器连接之后调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String a = "test";
//        ByteBuf mes = Unpooled.buffer(a.getBytes().length);
//        mes.writeByte(a.getBytes().length);
//        RpcRequest request = new RpcRequest();
//        request.setContext("aaaaa");
//        request.setId("12345");
//        ctx.writeAndFlush(request);
//        ctx.writeAndFlush(mes );
        if (rpcRequest!=null){
            ctx.writeAndFlush(rpcRequest);
        }
        System.out.println("客户端连接上服务器");
    }

    /**
     * 此方法会在接收到服务器数据后调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("客户端收到服务器响应数据");
        ByteBuf buf=(ByteBuf) msg;
        byte[] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8");
        System.out.println("Now is:"+body);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("客户端收到服务器响应数据处理完成");
    }
}
