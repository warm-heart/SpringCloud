package com.micro.consume.netty.server;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务端定时向客户端发送消息
 *
 * @author wangqianlong
 * create at: 2022/5/21
 */
public class ScheduleTask {

    private static Map<String, ChannelHandlerContext> CHANNEL_HANDLER_CONTEXT_MAP = new HashMap<>();

    private volatile static ScheduleTask SCHEDULE_TASK = null;


    private ScheduleTask() {
    }


    /**
     * 单例
     *
     * @return //
     */
    public static ScheduleTask getScheduleTask() {
        if (null == SCHEDULE_TASK) {
            synchronized (ScheduleTask.class) {
                if (null == SCHEDULE_TASK) {
                    SCHEDULE_TASK = new ScheduleTask();
                }
            }
        }
        return SCHEDULE_TASK;
    }

    /**
     * 放入连接
     *
     * @param channelId //
     * @param context   //
     */
    public void addChannelHandlerContext(String channelId, ChannelHandlerContext context) {
        CHANNEL_HANDLER_CONTEXT_MAP.put(channelId, context);
    }

    /**
     * 移除连接
     *
     * @param channelId //
     */
    public void removeChannelHandlerContext(String channelId) {
        CHANNEL_HANDLER_CONTEXT_MAP.remove(channelId);
    }


    /**
     * 获取连接
     *
     * @param channelId //
     * @return //
     */
    public ChannelHandlerContext getChannelHandlerContext(String channelId) {
        return CHANNEL_HANDLER_CONTEXT_MAP.get(channelId);
    }

    /**
     * 获取所有连接
     *
     * @return //
     */
    public List<ChannelHandlerContext> getAllChannelHandlerContext() {
        return (List<ChannelHandlerContext>) CHANNEL_HANDLER_CONTEXT_MAP.values();
    }

}
