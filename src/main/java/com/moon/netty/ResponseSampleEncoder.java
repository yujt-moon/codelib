package com.moon.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author yujiangtao
 * @date 2021/7/29 下午2:30
 */
public class ResponseSampleEncoder extends MessageToByteEncoder<ResponseSample> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseSample responseSample, ByteBuf byteBuf) throws Exception {
        if(responseSample != null) {
            byteBuf.writeBytes(responseSample.getCode().getBytes());
            byteBuf.writeBytes(responseSample.getData().getBytes());
            byteBuf.writeLong(responseSample.getTimestamp());
        }
    }
}
