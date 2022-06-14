package com.moon.rpc.netty;

import com.moon.rpc.transport.Header;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yujiangtao
 * @date 2021/2/3 上午10:27
 */
public class RequestEncoder extends CommandEncoder {

    private static final Logger logger = LoggerFactory.getLogger(RequestEncoder.class);

    @Override
    protected void encodeHeader(ChannelHandlerContext channelHandlerContext, Header header, ByteBuf byteBuf) throws Exception {
        logger.info("进入 RequestEncoder...");
        super.encodeHeader(channelHandlerContext, header, byteBuf);
    }
}
