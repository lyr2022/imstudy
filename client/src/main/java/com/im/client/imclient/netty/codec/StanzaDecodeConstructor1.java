package com.im.client.imclient.netty.codec;


import com.google.gson.Gson;
import com.im.client.imclient.spi.Payload;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.charset.Charset;
import java.util.List;


/**
 * 报文解码器构造类
 *
 */
public class StanzaDecodeConstructor1 extends ByteToMessageDecoder {


	private Gson gson = new Gson();

	private Charset charset = Charset.forName("UTF-8");


	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] bytes = new byte[in.readableBytes()];
		if (0 < bytes.length) {
			in.readBytes(bytes);
			String message = new String(bytes, this.charset);
			Payload payload = this.gson.fromJson(message, Payload.class);
			out.add(payload);
		}
	}
}
