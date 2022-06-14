package com.moon.rpc.transport;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author yujiangtao
 * @date 2021/1/30 下午2:59
 */
public class NettyTransport implements Transport {

    private final Channel channel;

    private final InFlightRequests inFlightRequests;

    private static final Logger logger = LoggerFactory.getLogger(NettyTransport.class);

    public NettyTransport(Channel channel, InFlightRequests inFlightRequests) {
        this.channel = channel;
        this.inFlightRequests = inFlightRequests;
    }

    @Override
    public CompletableFuture<Command> send(Command request) {
        // 构建返回值
        CompletableFuture<Command> completableFuture = new CompletableFuture<>();
        try {
            // 将在途请求放到 inFlightRequests 中
            inFlightRequests.put(new ResponseFuture(request.getHeader().getRequestId(), completableFuture));
            // 发送命令
            channel.writeAndFlush(request).addListener((ChannelFutureListener) channelFuture -> {
               // 处理发送失败的情况
               if(!channelFuture.isSuccess()) {
                   completableFuture.completeExceptionally(channelFuture.cause());
                   channel.close();
               }
            });
        } catch (Throwable t) {
            // 处理发送异常
            inFlightRequests.remove(request.getHeader().getRequestId());
            completableFuture.completeExceptionally(t);
        }
        return completableFuture;
    }
}
