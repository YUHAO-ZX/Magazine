/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author yuhao.zx
 * @version $Id: ExecutorTest.java, v 0.1 2018年11月28日 11:16 AM yuhao.zx Exp $
 */
public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 3, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque(1000));

        for(int i=0;i<10000;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("test");
                }
            });
        }

    }
}