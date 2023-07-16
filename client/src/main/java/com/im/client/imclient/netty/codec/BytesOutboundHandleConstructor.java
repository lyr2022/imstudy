package com.im.client.imclient.netty.codec;


import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 字节码流出处理器构造类
 * 处理粘包，半包问题
 */
public class BytesOutboundHandleConstructor extends LengthFieldPrepender {

	public BytesOutboundHandleConstructor() {
		super(2);
	}


}
