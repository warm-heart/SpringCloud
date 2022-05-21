package com.micro.consume.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author wangqianlong
 * create at: 2022/5/19
 */
public class Client {
    ChannelFuture channelFuture;

    public void start() throws Exception {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            //创建bootstrap对象，配置参数
            Bootstrap bootstrap = new Bootstrap();
            //设置线程组
            bootstrap.group(eventExecutors)
                    //设置客户端的通道实现类型
                    .channel(NioSocketChannel.class)
                    //使用匿名内部类初始化通道
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //添加客户端通道的处理器
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            System.out.println("客户端准备就绪，随时可以起飞~");
            //连接服务端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
            this.channelFuture = channelFuture;
            //对通道关闭进行监听
//            new Thread(() -> {
//                try {
//                    Thread.sleep(3000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.err.println("异步线程");
//
//                channelFuture.channel()
//                        .writeAndFlush(Unpooled.copiedBuffer("客户端往服务端再发一次消息", CharsetUtil.UTF_8));
//            }).start();
            channelFuture.channel().closeFuture().sync();

            //添加监听器
            channelFuture.addListener((ChannelFutureListener) future -> {
                //判断是否操作成功
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            });
        } finally {
            //关闭线程组
            eventExecutors.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.start();
        client.channelFuture.channel().writeAndFlush("客户端往服务端再发一次消息");
        System.err.println("over");
    }
}
