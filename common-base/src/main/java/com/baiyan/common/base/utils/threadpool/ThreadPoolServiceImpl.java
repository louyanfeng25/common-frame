package com.baiyan.common.base.utils.threadpool;

import com.alibaba.ttl.TtlRunnable;
import com.baiyan.common.base.exception.ThreadPoolExecutorException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池实现
 *
 * @author louyanfeng
 * @since 2020/11/13
 */
@Slf4j
public class ThreadPoolServiceImpl implements ThreadPoolService {

    /**
     * 主线程数
     */
    @Setter
    private int corePoolSize = 20;

    /**
     * 最大线程数
     */
    @Setter
    private int maximumPoolSize = 150;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    @Setter
    private long keepAliveTime = 60;

    /**
     * 单例线程池
     */
    private ThreadPoolExecutorFactory threadPoolExecutor;

    /**
     * 单例定时任务线程池
     */
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * 线程池所使用的缓冲队列的大小
     */
    @Setter
    private int queueSize = 100;
    @Setter
    private boolean inited = false;

    /**
     * 当线程池满时，是否阻塞住
     */
    @Setter
    private boolean blockWhenFull = true;

    /**
     * 初始化单例线程池
     */
    public void init() {
        if(inited) {
            return;
        }
        this.threadPoolExecutor = new ThreadPoolExecutorFactory(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize), new ThreadPoolServiceImpl.BlockingQueuePut());
        this.threadPoolExecutor.allowCoreThreadTimeOut(true);
        inited = true;
    }


    @Override
    public Future<?> addTask(Runnable task) {
        if(!inited) {
            init();
        }
        return threadPoolExecutor.submit(TtlRunnable.get(task));
    }

    /**
     * 线程池满时拒绝策略类
     */
    private class BlockingQueuePut implements RejectedExecutionHandler {
        /**
         * 拒绝策略时二次尝试增加队列任务，添加任务失败则进行异常报错
         * @param r
         * @param executor
         */
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if(blockWhenFull) {
                try {
                    executor.getQueue().put(r);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    @Override
    public int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }

    @Override
    public void stop() {
        threadPoolExecutor.shutdownNow();
        if(scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    @Override
    public synchronized void loopTask(Runnable task, long interval) {
        loopTask(task, interval, 0);
    }


    @Override
    public void loopTask(Runnable task, long interval, long delay) {
        if(scheduledExecutorService == null) {
            ThreadFactory threadFactory = new ThreadPoolServiceImpl.ScheduledThreadFactory("schedule-pool-%d-%s");
            scheduledExecutorService = Executors.newScheduledThreadPool(1, threadFactory);
        }
        int minInterval=100;
        if(interval < minInterval) {
            throw new IllegalArgumentException("不允许调度100ms以内的循环任务");
        }
        scheduledExecutorService.scheduleAtFixedRate(TtlRunnable.get(task), delay, interval, TimeUnit.MILLISECONDS);
    }

    @Override
    public void runTasksUntilEnd(List<Runnable> tasks) {
        List<Future<?>> futures = new ArrayList<Future<?>>();

        for(Runnable task : tasks) {
            futures.add(addTask(task));
        }

        for(Future<?> f : futures) {
            try {
                f.get();
            } catch (Exception e) {
                log.warn("", e);
            }
        }
    }

    /**
     * 获取单例线程池实例
     * @return
     */
    protected ThreadPoolExecutor getExecutorService() {
        return threadPoolExecutor;
    }

    static class ThreadPoolExecutorFactory extends ThreadPoolExecutor{
        /**
         * 构造方法
         *
         * @param corePoolSize
         * @param maximumPoolSize
         * @param keepAliveTime
         * @param unit
         * @param workQueue
         * @param handler
         */
        public ThreadPoolExecutorFactory(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue,
                                  RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                    Executors.defaultThreadFactory(), handler);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t == null && r instanceof Future<?>) {
                try {
                    Object result = ((Future<?>) r).get();
                } catch (CancellationException ce) {
                    t = ce;
                } catch (ExecutionException ee) {
                    t = ee.getCause();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            if (t != null)
                throw new ThreadPoolExecutorException(t);
        }
    }

    /**
     * 动态生成一个定时任务线程池
     */
    static class ScheduledThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        ScheduledThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = String.format(namePrefix, poolNumber.getAndIncrement(), "%d");
        }
        String getThreadName() {
            return String.format(namePrefix,
                    threadNumber.getAndIncrement());
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, getThreadName(), 0);
            if (!t.isDaemon()){
                t.setDaemon(true);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
