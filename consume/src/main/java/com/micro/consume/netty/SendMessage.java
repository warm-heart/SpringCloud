package com.micro.consume.netty;

import com.micro.consume.netty.client.MessageResult;
import io.netty.channel.Channel;

/**
 * @author wangqianlong
 * create at: 2022/7/3
 */
public class SendMessage {

    public static void send(Integer id, String content, Channel channel) {
        MessageResult.Message message = MessageResult
                .Message
                .newBuilder()
                .setId(id)
                .setContent(content)
                .build();
        channel.writeAndFlush(message);
    }
}
