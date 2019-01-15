/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.dateformat;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

/**
 *
 * @author yuhao.zx
 * @version $Id: Java8TimeTest.java, v 0.1 2018年11月29日 11:14 AM yuhao.zx Exp $
 */
public class Java8TimeTest {
    @Test
    public void testTime() {
        //关注与年月日
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);  //2017-06-26

        System.out.println(localDate.getYear()); //2017，年
        System.out.println(localDate.getMonthValue()); //6，月
        System.out.println(localDate.getDayOfMonth()); //26，日
        System.out.println(localDate.getDayOfWeek()); //MONDAY,星期几
        System.out.println(localDate.lengthOfMonth()); //30,返回当前月份的长度
        System.out.println(localDate.isLeapYear());//false,是否是闰年

        System.out.println("------------------");

        LocalDate localDate2 = LocalDate.of(2017,4,1);
        System.out.println(localDate2); //2017-04-01

        System.out.println("------------------");

        //MonthDay关注月份和日
        LocalDate localDate3 = LocalDate.of(2017,3,25);
        MonthDay monthDay = MonthDay.of(localDate3.getMonth(),localDate3.getDayOfMonth());
        System.out.println(monthDay); //--03-25
        MonthDay monthDay2 = MonthDay.from(LocalDate.of(2014,3,25));
        System.out.println(monthDay2); //--03-25

        if(monthDay.equals(monthDay2)){
            System.out.println("equal");
        }else{
            System.out.println("not equal");
        }

        //关注与时分秒
        LocalTime time = LocalTime.now();
        System.out.println(time); //22:30:01.512
        System.out.println(time.getHour()); //22,时
        System.out.println(time.getMinute()); //30，分
        System.out.println(time.getSecond());  //01，秒


        LocalTime time2 = time.plusHours(3).plusMinutes(40);
        System.out.println(time2);  //02:10:01.512

    }

}