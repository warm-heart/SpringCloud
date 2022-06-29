package com.micro.consume.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.LineSeparator;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

/**
 * @author wangqianlong
 * create at: 2022/5/19
 */
public class Client {
    ChannelFuture channelFuture;
    NioEventLoopGroup eventExecutors;

    public void start() throws Exception {
        eventExecutors = new NioEventLoopGroup();
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
                            ch.pipeline()
                                    //拆包解码
                                    //.addLast(new ProtobufVarint32FrameDecoder())
                                    // .addLast(new ProtobufDecoder(CIMResponseProto.CIMResProtocol.getDefaultInstance()))
                                    //拆包编码
                                    //.addLast(new ProtobufVarint32LengthFieldPrepender())
                                    //.addLast(new ProtobufEncoder())
                                    //业务处理器
                                    //添加编码器，使用默认的符号\n，字符集是UTF-8
                                    .addLast(new LineEncoder(LineSeparator.DEFAULT, CharsetUtil.UTF_8))
                                    .addLast(new ClientHandler());
                        }
                    });
            System.out.println("客户端准备就绪，随时可以起飞~");
            //连接服务端
            this.channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();

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
//            channelFuture.channel().closeFuture().sync();
            //添加监听器
//            channelFuture.addListener((ChannelFutureListener) future -> {
//                //判断是否操作成功
//                if (future.isSuccess()) {
//                    System.out.println("连接成功");
//                } else {
//                    System.out.println("连接失败");
//                }
//            });
        } finally {
            //关闭线程组
//            eventExecutors.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.start();
        client.channelFuture.channel()
                .writeAndFlush(Unpooled.copiedBuffer("客户端往服务端再发一次消息" + StringUtil.NEWLINE, CharsetUtil.UTF_8));
        client.channelFuture.channel().closeFuture().sync();
        client.eventExecutors.shutdownGracefully();
        System.err.println("over");
    }
}
