package com.im.client.imclient.netty;

import com.im.client.imclient.netty.codec.*;
import com.im.client.imclient.netty.dispatcher.PayloadResultFuture;
import com.im.client.imclient.netty.dispatcher.RequestPendingCenter;
import com.im.client.imclient.netty.handler.MsgProcessHandler;
import com.im.client.imclient.netty.handler.ResponseDispatcherHandler;
import com.im.client.imclient.spi.Payload;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
public class ClientComponent {

    private ChannelFuture channelFuture= null;



    public void init(){
        try {
            clientStart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void clientStart() throws ExecutionException, InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(new NioEventLoopGroup());
        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new BytesInboundHandleConstructor());
                pipeline.addLast(new BytesOutboundHandleConstructor());
                pipeline.addLast(new StanzaEncodeConstructor());
                pipeline.addLast(new StanzaDecodeConstructor());
                pipeline.addLast(new MsgProcessHandler());
                pipeline.addLast(new ResponseDispatcherHandler(requestPendingCenter));
            }
        });
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090);
        channelFuture.sync();// 等待连接完成
        this.channelFuture = channelFuture;

//        Payload payload = new Payload();
//        Payload.Header head = new Payload.Header();
//        head.setFrom("c001");
//        head.setTo("c002");
//        payload.setHead(head);
//        Map<String, Object> body = new HashMap<>();
//        body.put("msg","hello, world");
//        payload.setBody(body);
//        PayloadResultFuture payloadResultFuture =  new PayloadResultFuture();
//        requestPendingCenter.add("c001",payloadResultFuture);
//        channelFuture.channel().writeAndFlush(payload);
//        Payload payload1 = null;
//        try {
//            payload1 = payloadResultFuture.get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(payload1);





//        channelFuture.channel().closeFuture().get();
    }

    public void sendMsg(String msg){
        if(channelFuture != null){
            Payload payload = new Payload();
            Payload.Header head = new Payload.Header();
            head.setFrom("c002");
            head.setTo("c003");
            payload.setHead(head);
            Map<String, Object> body = new HashMap<>();
            body.put("msg","hello, world, i'm 002");
            payload.setBody(body);
            PayloadResultFuture payloadResultFuture =  new PayloadResultFuture();
//            requestPendingCenter.add("c001",payloadResultFuture);
            channelFuture.channel().writeAndFlush(payload);
            Payload payload1 = null;
            try {
                payload1 = payloadResultFuture.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(payload1);
        }
    }

}
