package com.micro.produce.monitor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import sun.management.ManagementFactoryHelper;

import java.lang.management.*;
import java.util.List;

/**
 * @author wangqianlong
 * create at: 2022/6/25
 */
@Component
public class JVMMonitor implements InitializingBean {

    public void monitor() {
        threadInfo();
        memoryManager();
        gcManager();
        bufferPoolManager();

    }

    public synchronized void threadInfo() {
        ThreadInfo threadInfo = ManagementFactory.getThreadMXBean().getThreadInfo(Thread.currentThread().getId());
        LockInfo lockInfo = threadInfo.getLockInfo();
        System.err.println(lockInfo);

    }

    public void memoryManager() {
        List<MemoryManagerMXBean> memoryManagerMXBeans = ManagementFactory.getMemoryManagerMXBeans();
        System.err.println(memoryManagerMXBeans);
    }

    public void gcManager() {
        List<GarbageCollectorMXBean> garbageCollectors = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollector : garbageCollectors) {
            System.err.println(garbageCollector.getCollectionTime());
        }
    }

    public void bufferPoolManager() {
        List<BufferPoolMXBean> bufferPoolMXBeans = ManagementFactoryHelper.getBufferPoolMXBeans();
        System.err.println(bufferPoolMXBeans);
    }


    @Override
    public void afterPropertiesSet() {
        this.monitor();
    }
}
