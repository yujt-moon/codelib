package com.moon.rpc.netty;

import com.moon.rpc.transport.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yujiangtao
 * @date 2021/2/4 下午3:22
 */
public class RequestDecoder extends CommandDecoder {

    private static final Logger logger = LoggerFactory.getLogger(RequestDecoder.class);

    @Override
    protected Header decodeHeader(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        logger.info("进入 RequestDecoder...");
        return new Header(
                byteBuf.readInt(),
                byteBuf.readInt(),
                byteBuf.readInt()
        );
    }
}
