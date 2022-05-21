package com.micro.consume.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author wangqianlong
 * create at: 2022/5/19
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //发送消息到服务端
        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端连接到服务端事件消息；" +
                "你好服务端，我是客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //接收服务端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到服务端" + ctx.channel().remoteAddress() + "的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }
}
