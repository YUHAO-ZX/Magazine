/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.dateformat;


import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yuhao.zx
 * @version $Id: DateTest.java, v 0.1 2018年11月28日 9:16 PM yuhao.zx Exp $
 */
public class DateTest {

    /**
     *
     *
     * @throws ParseException
     */
    @Test
    public void testThreadLocalDateFormat() throws ParseException {
        for(int i=0;i <1000;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = null;
                    try {
                        s = ThreadLocalSimpleDateFormat.formatDate(new Date());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s);
                }
            });
            t.start();
        }

    }

    /**
     * 线程池使用ThreadLocal保存非线程安全的数据
     *
     * @throws InterruptedException
     */
    @Test
    public void testThreadPool() throws InterruptedException {

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 10, 3, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque(100));
        executorService.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejected");
            }
        });


        for(int i=0;i <1000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String s = null;
                    try {
                        s = ThreadLocalSimpleDateFormat.formatDate(new Date());
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s);
                }
            });
        }

        Thread.sleep(10000);
    }
}