package com.moon.rpc.netty;

import com.moon.rpc.transport.Command;
import com.moon.rpc.transport.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author yujiangtao
 * @date 2021/2/3 上午9:59
 */
public abstract class CommandDecoder extends ByteToMessageDecoder {
    private static final int LENGTH_FIELD_LENGTH = Integer.BYTES;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        if(!byteBuf.isReadable(LENGTH_FIELD_LENGTH)) {
            return;
        }
        byteBuf.markReaderIndex();
        int length = byteBuf.readInt() - LENGTH_FIELD_LENGTH;

        if(byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }

        Header header = decodeHeader(channelHandlerContext, byteBuf);
        int payloadLength = length - header.length();
        byte[] payload = new byte[payloadLength];
        byteBuf.readBytes(payload);
        list.add(new Command(header, payload));
    }

    protected abstract Header decodeHeader(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf);
}
