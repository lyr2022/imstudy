package com.im.server.imserver.netty;

import com.im.server.imserver.netty.codec.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * n
 */
public class ClientEndpointComponent {


    ChannelFuture channelFuture;


    public void init(){
        start();
    }



    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        serverBootstrap.group(new NioEventLoopGroup());
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new BytesInboundHandleConstructor());
                pipeline.addLast(new BytesOutboundHandleConstructor());
                pipeline.addLast(new StanzaEncodeConstructor());
                pipeline.addLast(new StanzaDecodeConstructor());
                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new PayloadAccepter());

            }
        });
        try {
            this.channelFuture = serverBootstrap.bind(8090).sync();
        } catch (Exception e) {
            try {
                channelFuture.channel().close().sync();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
