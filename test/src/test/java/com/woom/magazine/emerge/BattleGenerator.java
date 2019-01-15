/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.woom.magazine.emerge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author yuhao.zx
 * @version $Id: BattleGenerator.java, v 0.1 2019年01月09日 11:56 AM yuhao.zx Exp $
 */
public class BattleGenerator {

    /** 一天的毫秒数 */
    private static Long         ONE_DAY_MILLISECOND  = 24 * 60 * 60 * 1000L;

    /** 一小时的毫秒数 */
    private static Long         ONE_HOUR_MILLISECOND = 60 * 60 * 1000L;

    private static final String activityCode         = "minestar2019newyear";

    public static void main(String[] args) throws ParseException {
        String startDay = "20190131";
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start = dayFormat.parse(startDay);

        int battleSize = 7;

        String template = "{\"activityCode\":\"%s\",\"battleCode\":\"%s_%s\",\"battleStartTime\":\"%s\",\"battleEndTime\":\"%s\"},";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < battleSize; i++) {
            sb.append(String.format(template, activityCode, activityCode, dayFormat.format(start),
                dateFormat.format(start),
                dateFormat.format(new Date(start.getTime() + ONE_DAY_MILLISECOND))));

            start = new Date(start.getTime() + ONE_DAY_MILLISECOND);
        }

        String rs = sb.toString();
        System.out.println(rs.substring(0, rs.length() - 1) + "]");

        tt();
    }

    public static void tt() throws ParseException {
        String startDay = "20190119_00";
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd_HH");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date start = dayFormat.parse(startDay);

        int battleSize = 2;

        String template = "{\"activityCode\":\"%s\",\"battleCode\":\"%s_%s\",\"battleStartTime\":\"%s\",\"battleEndTime\":\"%s\"},";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < battleSize; i++) {
            for (int j = 0; j < 24; j++) {

                sb.append(String.format(template, activityCode, activityCode,
                    dayFormat.format(start), dateFormat.format(start),
                    dateFormat.format(new Date(start.getTime() + ONE_HOUR_MILLISECOND))));

                start = new Date(start.getTime() + ONE_HOUR_MILLISECOND);
            }

        }

        String rs = sb.toString();
        System.out.println(rs.substring(0, rs.length() - 1) + "]");
    }
}