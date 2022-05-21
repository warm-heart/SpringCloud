package com.micro.consume.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangqianlong
 * create at: 2022/5/19
 */
@Slf4j
public class Server {


    public static void main(String[] args) throws Exception {
        //创建两个线程组 boosGroup、workerGroup
        //bossGroup 用于监听客户端连接，专门负责与客户端创建连接，并把连接注册到workerGroup的Selector中。
        //workerGroup用于处理每一个连接发生的读写事件。
        EventLoopGroup bossGroup = new NioEventLoopGroup(100);
        EventLoopGroup workerGroup = new NioEventLoopGroup(5);
        try {
            //创建服务端的启动对象，设置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置两个线程组boosGroup和workerGroup
            bootstrap.group(bossGroup, workerGroup)
                    //设置服务端通道实现类型
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到连接个数
                    //option()设置的是服务端用于接收进来的连接，也就是boosGroup线程。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //设置保持活动连接状态
                    // childOption()是提供给父管道接收到的连接，也就是workerGroup线程。
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_RCVBUF, 10240)
                    //使用匿名内部类的形式初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            //给pipeline管道设置处理器
                            socketChannel.pipeline()
                                    .addFirst(new DecodeHandle())
                                    .addLast(new ServerHandle());
                        }
                    });//给workerGroup的EventLoop对应的管道设置处理器
            System.out.println("服务端已经准备就绪...");
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();

            //启动定时任务
//            ScheduleTask.getScheduleTask().run();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
