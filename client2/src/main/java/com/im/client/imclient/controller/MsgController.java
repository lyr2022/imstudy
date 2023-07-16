package com.im.client.imclient.controller;

import com.im.client.imclient.netty.ClientComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MsgController {

    @Autowired
    ClientComponent clientComponent;

    @RequestMapping("/send2")
    public void sendMsg(String msg) {

        clientComponent.sendMsg(msg);

    }
}
