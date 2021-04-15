package com.baiyan.common.base.utils.threadpool;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 线程池
 *
 * @author baiyan
 * @since 2020/3/23
 */
public interface ThreadPoolService {
    /**
     * 向线程池中添加任务
     * @param task 任务
     * @return 任务(必须实现Runnable接口)
     */
    Future<?> addTask(Runnable task);

    /**
     * 异步执行一批任务，直到任务执行完成
     * @param task 任务
     */
    void runTasksUntilEnd(List<Runnable> task);

    /**
     * 向线程池中添加循环运行的任务
     * @param task 任务(必须实现Runnable接口)
     * @param interval 时间间隔，单位毫秒
     */
    void loopTask(Runnable task, long interval);

    /**
     * 向线程池中添加循环运行的任务
     * @param task 任务(必须实现Runnable接口)
     * @param interval 时间间隔，单位毫秒
     * @param delay 延迟执行的时间，单位毫秒，表示任务在delay ms后开始被定时调度
     */
    void loopTask(Runnable task, long interval, long delay);

    /**
     * 停止线程池
     */
    void stop();

    /**
     * 获取线程池中正在执行的线程数目
     * @return 激活线程数
     */
    int getActiveCount();

}
