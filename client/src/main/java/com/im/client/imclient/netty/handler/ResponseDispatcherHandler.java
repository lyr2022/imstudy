package com.im.client.imclient.netty.handler;

import com.im.client.imclient.netty.dispatcher.RequestPendingCenter;
import com.im.client.imclient.spi.Payload;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理响应分发
 */
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<Payload> {

    private RequestPendingCenter requestPendingCenter;

    public ResponseDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Payload payload) throws Exception {


        requestPendingCenter.set( "c001",payload);
    }
}
