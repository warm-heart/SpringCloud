package com.micro.consume.netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 服务端定时向客户端发送消息
 *
 * @author wangqianlong
 * create at: 2022/5/21
 */
@Slf4j
public class ScheduleTask {

    private static final Map<String, ChannelHandlerContext> CHANNEL_HANDLER_CONTEXT_MAP = new HashMap<>();

    private volatile static ScheduleTask SCHEDULE_TASK = null;

    private final static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);


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


    public void run() {
        Runnable task = new Thread(() -> {
            try {
                List<ChannelHandlerContext> channelHandlerContexts = getAllChannelHandlerContext();
                channelHandlerContexts.forEach(this::sendMessage);
            } catch (Throwable e) {
                log.error("sendMessage exception", e);
            }
        });
        //只运行一次
        //scheduledExecutorService.schedule(task);
        //是以上一个任务开始的时间计时，120秒过去后，检测上一个任务是否执行完毕，
        // 如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行，
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 5, TimeUnit.SECONDS);

        //上一次结束时间 + 延迟时间 = 下一次开始时间
//        scheduledExecutorService.scheduleWithFixedDelay(task, 1, 5, TimeUnit.SECONDS);
    }

    private void sendMessage(ChannelHandlerContext ctx) {
        ctx.channel()
                .writeAndFlush(Unpooled.copiedBuffer("服务端定时向客户端发送消息", CharsetUtil.UTF_8));
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

        return CHANNEL_HANDLER_CONTEXT_MAP.values().stream()
                .map(Function.identity())
                .collect(Collectors.toList());
    }

}
