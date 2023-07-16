package com.im.server.imserver.netty.codec;

import com.im.server.imserver.netty.Session;
import com.im.server.imserver.spi.Payload;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PayloadAccepter extends SimpleChannelInboundHandler<Payload> {

    private final AttributeKey<Session> clientSession = AttributeKey.valueOf("CLIENT_SESSION");

   static Map<String,ChannelHandlerContext> addressContext = new HashMap<>();


    /**
     * 连接的时候会调用这个方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Session session = new Session(ctx);
        // 维护
        Attribute<Session> attr = ctx.attr(clientSession);
        attr.set(session);
    }


    /**
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Payload payload) throws Exception {


//        ctx.writeAndFlush(payload);

        Payload.Header head = payload.getHead();
        String from = head.getFrom();
        ChannelHandlerContext channelHandlerContext1 = addressContext.get(from);
        if(channelHandlerContext1 == null){
            addressContext.put(from,ctx);
        }
        Attribute<Session> attribute = ctx.attr(clientSession);
        Session session = attribute.get();
        if (null == session) {
            ctx.close().get(10, TimeUnit.SECONDS);
        } else {
//             可以取出context然后进行处理
            ChannelHandlerContext context = session.getContext();
            String to = payload.getHead().getTo();
            ChannelHandlerContext channelHandlerContext = addressContext.get(to);
            if(channelHandlerContext!=null){
                channelHandlerContext.writeAndFlush(payload);
            }else {
                System.out.println("没有连接信息");
            }
        }
    }


}
