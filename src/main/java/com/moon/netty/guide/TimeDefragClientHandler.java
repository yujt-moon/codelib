package com.moon.netty.guide;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author yujiangtao
 * @date 2021/7/31 下午12:15
 */
public class TimeDefragClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf byteBuf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        byteBuf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        byteBuf.release();
        byteBuf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        byteBuf.writeBytes(m);
        m.release();

        if(byteBuf.readableBytes() >= 4) {
            long currentTimeMillis = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
