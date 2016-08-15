package com.star.fly.coder;


import com.star.fly.RpcRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by  wuyunxing on   2016/7/14.
 */

public class RpcEncoder extends MessageToByteEncoder <RpcRequest>{
    @Override
    protected void encode(ChannelHandlerContext ctx, RpcRequest rpcRequest, ByteBuf byteBuf) throws Exception {
        byte[] body = SerializaUtil.objectTobyte(rpcRequest);
        int dataLength = body.length;  //读取消息的长度
        byteBuf.writeInt(dataLength);  //先将消息长度写入，也就是消息头，4个字节
        byteBuf.writeBytes(body);  //消息体中包含我们要发送的数据
        ctx.flush();
    }


}
