package com.im.server.imserver.netty.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 字节码流入处理器构造类
 * 处理粘包，半包问题
 */
public class BytesInboundHandleConstructor extends LengthFieldBasedFrameDecoder {


	public BytesInboundHandleConstructor() {
		super(Integer.MAX_VALUE, 0, 2, 0, 2);
	}


}
