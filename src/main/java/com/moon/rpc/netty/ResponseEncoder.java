package com.moon.rpc.netty;

import com.moon.rpc.transport.Header;
import com.moon.rpc.transport.ResponseHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * 响应编码器
 *
 * @author yujiangtao
 * @date 2021/2/4 下午3:24
 */
public class ResponseEncoder extends CommandEncoder {

    private static final Logger logger = LoggerFactory.getLogger(ResponseEncoder.class);

    @Override
    protected void encodeHeader(ChannelHandlerContext channelHandlerContext, Header header, ByteBuf byteBuf) throws Exception {
        logger.info("进入 ResponseEncoder...");
        super.encodeHeader(channelHandlerContext, header, byteBuf);
        if(header instanceof ResponseHeader) {
            ResponseHeader responseHeader = (ResponseHeader) header;
            byteBuf.writeInt(responseHeader.getCode());
            int errorLength = responseHeader.length() - (Integer.BYTES + Integer.BYTES + Integer.BYTES
                    + Integer.BYTES + Integer.BYTES);
            byteBuf.writeInt(errorLength);
            byteBuf.writeBytes(responseHeader.getError() == null ? new byte[0] :
                    responseHeader.getError().getBytes(StandardCharsets.UTF_8));
        } else {
            throw new Exception(String.format("Invalid header type: %s!", header.getClass().getCanonicalName()));
        }
    }
}
