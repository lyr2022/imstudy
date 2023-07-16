package com.im.client.imclient.netty.handler;

import com.im.client.imclient.spi.Payload;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * 处理业务逻辑，使用SimpleChannelInboundHandler可以帮助我们自动释放byteBuffer
 */
public class MsgProcessHandler extends SimpleChannelInboundHandler<Payload> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Payload msg) throws Exception {
        System.out.println("----" + msg.getBody().get("msg"));
    }
}
