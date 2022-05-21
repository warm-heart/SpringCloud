package com.micro.consume.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * 自定义的Handler需要继承Netty规定好的HandlerAdapter
 * 才能被Netty框架所关联，有点类似SpringMVC的适配器模式
 **/
@Slf4j
public class ServerOutHandle extends ChannelOutboundHandlerAdapter {

    @Override
    public void connect(ChannelHandlerContext ctx,
                        SocketAddress remoteAddress,
                        SocketAddress localAddress,
                        ChannelPromise promise) throws Exception {
        log.info("服务端connect" + "------" + remoteAddress.toString());
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("服务端write" + "--------" + byteBuf.toString(CharsetUtil.UTF_8));
        super.write(ctx, msg, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端read");
        super.read(ctx);
    }
}