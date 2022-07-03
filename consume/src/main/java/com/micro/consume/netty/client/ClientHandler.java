package com.micro.consume.netty.client;

import com.micro.consume.netty.SendMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author wangqianlong
 * create at: 2022/5/19
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //发送消息到服务端
        SendMessage.send(1, "message", ctx.channel());
        SendMessage.send(2, "message", ctx.channel());
//        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端连接到服务端事件消息；" +
//                "你好服务端，我是客户端" + StringUtil.NEWLINE, CharsetUtil.UTF_8));
//        ctx.writeAndFlush(Unpooled.copiedBuffer("第二" + StringUtil.NEWLINE, CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.fireChannelRead(msg);
        //接收服务端发送过来的消息
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("收到服务端" + ctx.channel().remoteAddress() + "的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }
}
