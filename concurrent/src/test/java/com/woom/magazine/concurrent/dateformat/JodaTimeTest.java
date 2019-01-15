/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.dateformat;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Date;

/**
 *
 * @author yuhao.zx
 * @version $Id: JodaTimeTest.java, v 0.1 2018年11月29日 11:02 AM yuhao.zx Exp $
 */
public class JodaTimeTest {
    @Test
    public void testJoda() {
        DateTime today = new DateTime(new Date());
        System.out.println(today.toString("yyyy-MM-dd HH:mm:ss.SSS"));


        LocalDate localDate = new LocalDate();
        localDate = localDate.plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);


    }
}