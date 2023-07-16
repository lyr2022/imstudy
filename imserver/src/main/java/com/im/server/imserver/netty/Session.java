package com.im.server.imserver.netty;


import io.netty.channel.ChannelHandlerContext;

/**
 * 会话
 * 维护连接状态
 */
public class Session {

    private ChannelHandlerContext context = null;


    public Session(ChannelHandlerContext context) {
        this.context = context;
    }


    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }
}