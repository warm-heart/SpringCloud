package com.micro.consume.netty.server;

import com.micro.consume.netty.client.MessageResult;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * 自定义的Handler需要继承Netty规定好的HandlerAdapter
 * 才能被Netty框架所关联，有点类似SpringMVC的适配器模式
 **/
@Slf4j
public class ServerHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("服务端channelRead");
        //获取客户端发送过来的消息
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));


        MessageResult.Message message = (MessageResult.Message) msg;
        System.err.println(message.getId() + message.getContent());


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
        // ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已收到消息，并给你发送一个ack", CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("服务端channelActive");
        String channelId = ctx.channel().id().asLongText();
        ScheduleTask.getScheduleTask().addChannelHandlerContext(channelId, ctx);
    }

    /**
     * 连接关闭事件
     *
     * @param ctx //
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //发送消息给客户端
        log.info("服务端channelInactive");
        String channelId = ctx.channel().id().asLongText();
        ScheduleTask.getScheduleTask().removeChannelHandlerContext(channelId);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //发生异常，关闭通道
        ctx.close();
        String channelId = ctx.channel().id().asLongText();
        ScheduleTask.getScheduleTask().removeChannelHandlerContext(channelId);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        // 当连接空闲时间太长时，将会触发一个 IdleStateEvent 事件
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.ALL_IDLE)) {
                final String remoteAddress = parseChannelRemoteAddr(ctx.channel());
                log.info(remoteAddress + "------连接空闲时间太长，IdleStateEvent事件------");
                ctx.channel().close();
            }
        }
        ctx.fireUserEventTriggered(evt);
    }


    //---------------私有方法分界线-------------


    public static String parseChannelRemoteAddr(Channel channel) {
        if (null == channel) {
            return "";
        } else {
            SocketAddress remote = channel.remoteAddress();
            String addr = remote != null ? remote.toString() : "";
            if (addr.length() > 0) {
                int index = addr.lastIndexOf("/");
                return index >= 0 ? addr.substring(index + 1) : addr;
            } else {
                return "";
            }
        }
    }

}