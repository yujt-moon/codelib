package com.moon.rpc.netty;

import com.moon.rpc.transport.Command;
import com.moon.rpc.transport.InFlightRequests;
import com.moon.rpc.transport.ResponseFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yujiangtao
 * @date 2021/2/3 上午10:39
 */
@ChannelHandler.Sharable
public class ResponseInvocation extends SimpleChannelInboundHandler<Command> {

    private static final Logger logger = LoggerFactory.getLogger(ResponseInvocation.class);

    private final InFlightRequests inFlightRequests;

    public ResponseInvocation(InFlightRequests inFlightRequests) {
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command response) throws Exception {
        ResponseFuture future = inFlightRequests.remove(response.getHeader().getRequestId());
        if(null != future) {
            future.getFuture().complete(response);
        } else {
            logger.warn("Drop response: {}", response);
        }
    }
}
