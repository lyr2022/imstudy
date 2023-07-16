package com.im.server.imserver.config;

import com.im.server.imserver.netty.ClientEndpointComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyInitConfig {
    @Bean(initMethod = "init")
    public ClientEndpointComponent initNettyServer(){
        return new ClientEndpointComponent();
    }
}
