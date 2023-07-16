package com.im.client.imclient.netty.codec;

import com.google.gson.Gson;
import com.im.client.imclient.spi.Payload;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**

 */
public class StanzaEncodeConstructor extends MessageToByteEncoder<Payload> {

	private Gson gson = new Gson();

	private Charset charset = Charset.forName("UTF-8");

	@Override
	protected void encode(ChannelHandlerContext ctx, Payload payload, ByteBuf out) throws Exception {
		String message = this.gson.toJson(payload);
		byte[] bytes = message.getBytes(this.charset);
		out.writeBytes(bytes);
	}


}
