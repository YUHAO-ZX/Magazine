/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.future;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author yuhao.zx
 * @version $Id: FutureTaskHolderTest.java, v 0.1 2018年10月13日 下午1:43 yuhao.zx Exp $
 */
public class FutureTaskHolderTest {

    @Test
    public void testNormal() {
        buildAndRun(1,2,100,100,false,100);

    }

    @Test
    public void testTimeOut() {
        buildAndRun(10,100,10,100,true,110);

    }

    @Test
    public void testCorePoolSize() {
        buildAndRun(10,100,10,30,true,110);

    }

    private void buildAndRun(int cs,int ms,int que,final int timeOut,final boolean needTimeOut,int loopTimes){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cs);
        executor.setMaxPoolSize(ms);
        executor.setQueueCapacity(que);
        executor.initialize();

        List<FutureTaskHolder<String>> futureTaskHolderList = new ArrayList<FutureTaskHolder<String>>();
        for (int i = 0; i < loopTimes; i++) {
            FutureTaskHolder<String> taskHolder = new FutureTaskHolder(executor);
            futureTaskHolderList.add(taskHolder);
            final int m = i;
            taskHolder.submit(new ConcurrentCallBack() {
                public String process() {
                    if(needTimeOut && m % 5 == 0){
                        try {
                            Thread.sleep(timeOut+100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return "test-holder-" + m;
                }
            });
        }

        for(FutureTaskHolder<String> ele : futureTaskHolderList){
            String rs = ele.waitDone(timeOut, TimeUnit.MILLISECONDS);
            System.out.println(rs);
        }


    }
}