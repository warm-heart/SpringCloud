package com.micro.consume.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义的Handler需要继承Netty规定好的HandlerAdapter
 * 才能被Netty框架所关联，有点类似SpringMVC的适配器模式
 **/
public class ServerHandle extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //获取客户端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));

        new Thread(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("服务端异步线程开始执行-----");

            ctx.channel()
                    .writeAndFlush(Unpooled.copiedBuffer("服务端延时往客户端再发一次消息", CharsetUtil.UTF_8));
        }).start();

//        //获取到线程池eventLoop（bossGroup），添加线程，执行
//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                //长时间操作，不至于长时间的业务操作导致Handler阻塞
//                Thread.sleep(1000);
//                System.out.println("长时间的业务处理");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //发送消息给客户端
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息，并给你发送一个ack", CharsetUtil.UTF_8));
    }


    /**
     * 连接关闭事件
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //发送消息给客户端
        System.err.println("连接关闭");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //发生异常，关闭通道
        ctx.close();
    }
}