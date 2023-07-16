package com.im.client.imclient.config;

import com.im.client.imclient.netty.ClientComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyInitConfig {
    @Bean(initMethod = "init")
    public ClientComponent initNettyServer(){
        return new ClientComponent();
    }
}
