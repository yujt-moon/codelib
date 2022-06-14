package com.moon.netty.guide;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author yujiangtao
 * @date 2021/8/1 上午11:19
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4) {
            return;
        }
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
