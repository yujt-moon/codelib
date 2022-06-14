package com.moon.rpc.netty;

import com.moon.rpc.transport.Header;
import com.moon.rpc.transport.ResponseHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 响应解码器
 *
 * @author yujiangtao
 * @date 2021/2/3 上午10:12
 */
public class ResponseDecoder extends CommandDecoder {

    private static final Logger logger = LoggerFactory.getLogger(ResponseDecoder.class);

    @Override
    protected Header decodeHeader(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        logger.info("进入 ResponseDecoder...");
        int type = byteBuf.readInt();
        int version = byteBuf.readInt();
        int requestId = byteBuf.readInt();
        int code = byteBuf.readInt();
        int errorLength = byteBuf.readInt();
        byte[] errorBytes = new byte[errorLength];
        byteBuf.readBytes(errorBytes);
        String error = new String(errorBytes, StandardCharsets.UTF_8);
        return new ResponseHeader(requestId, version, type, code, error);
    }
}
