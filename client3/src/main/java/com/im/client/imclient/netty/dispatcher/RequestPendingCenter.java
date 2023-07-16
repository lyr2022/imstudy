package com.im.client.imclient.netty.dispatcher;

import com.im.client.imclient.spi.Payload;

import java.util.HashMap;
import java.util.Map;

public class RequestPendingCenter {

    private Map<String, PayloadResultFuture> map = new HashMap<>();


    public void add(String streamId, PayloadResultFuture operationResultFuture) {
        map.put(streamId, operationResultFuture);
    }

    public void set(String streamId, Payload payload) {
        PayloadResultFuture operationResultFuture = map.get(streamId);
        if (operationResultFuture != null) {
            operationResultFuture.setSuccess(payload);
//            map.remove(streamId);
        }
    }
}
