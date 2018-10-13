/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * future task
 *
 * @author yuhao.zx
 * @version $Id: FutureTaskHolder.java, v 0.1 2018年10月13日 下午1:17 yuhao.zx Exp $
 */
public class FutureTaskHolder<T> {

    /** thread pool */
    protected ThreadPoolTaskExecutor threadPool    = null;

    /** Future */
    protected Future<T>              futureTask;

    /** submitTime */
    protected long                   submitTime    = 0;

    /**callTime */
    protected long                   callTime      = 0;

    protected Throwable              lastException = null;

    public FutureTaskHolder(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public void submit(final ConcurrentCallBack<T> callback) {
        submitTime = System.currentTimeMillis();

        futureTask = threadPool.submit(new Callable<T>() {
            public T call() throws Exception {
                callTime = System.currentTimeMillis();
                try {
                    return callback.process();
                } catch (final Throwable e) {
                    lastException = e;
                    e.printStackTrace();
                } finally {

                }

                return null;
            }
        });
    }

    public T waitDone(long timeout, TimeUnit timeUnit) {
        T result;
        try {

            result = futureTask.get(timeout, timeUnit);

        } catch (TimeoutException te) {

            long exceptionTime = System.currentTimeMillis();

            futureTask.cancel(true);

            long waitTime = callTime - submitTime;

            long executeTime = exceptionTime - callTime;

            int activeCount = threadPool.getActiveCount();

            int poolSize = threadPool.getPoolSize();

            int maxPoolSize = threadPool.getMaxPoolSize();

            int queueSize = 0;

            ThreadPoolExecutor threadPoolExecutor = threadPool.getThreadPoolExecutor();

            if (threadPoolExecutor != null) {
                queueSize = threadPoolExecutor.getQueue().size();
            }

            System.out.println(String.format(
                "线程执行异常，队列等待时间:%s ms,实际执行时间:%s ms,活跃线程数:%s,线程池大小:%s,最大线程大小:%s，队列中任务数:%s", waitTime,
                executeTime, activeCount, poolSize, maxPoolSize, queueSize));

            throw new RuntimeException("thread time out", te);
        } catch (Throwable e) {
            futureTask.cancel(true);
            throw new RuntimeException(e);
        }

        if (null != lastException) {
            throw new RuntimeException(lastException);
        }

        return result;
    }

}