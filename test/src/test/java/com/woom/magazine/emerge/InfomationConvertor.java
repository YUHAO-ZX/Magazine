/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.emerge;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author yuhao.zx
 * @version $Id: InfomationConvertor.java, v 0.1 2018年12月28日 4:13 PM yuhao.zx Exp $
 */
public class InfomationConvertor {

    public static Information convert(String[] values) throws ParseException {

        Information information = new Information();
        information.setId(Integer.valueOf(values[0]));
        information.setContentName(values[1].trim());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        information.setStartTime(sd.parse(values[2].trim()));
        information.setEndTime(sd.parse(values[3].trim()));
        information.setContentDescription(values[4].trim());
        information.setButtonDesc(values[5].trim());
        information.setButtonUrl(values[6].trim());
        information.setCardUrl(values[7].trim());
        information.setMainTitle(values[8].trim());
        information.setPromoInfo(values[9].trim());
        information.setRemark(values[10].trim());
        information.setLogo(values[11].trim());
        information.setPromoLogo(values[12].trim());
        information.setItemId(values.length >= 14 ? values[13].trim() : null);
        checkInformation(information);

        return information;
    }

    private static void checkInformation(Information information) throws ParseException {
        Assert.isTrue(information.getId() >= 0 && information.getId() <= 9999,
            "id need >=0 && <= 9999");
        Assert.isTrue(StringUtils.isNotBlank(information.getContentName()), "name need not blank");

        Assert.isTrue(StringUtils.isNotBlank(information.getContentDescription()),
            "description need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getButtonDesc()),
            "button desc need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getButtonUrl()),
            "button url need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getCardUrl()), "card url need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getMainTitle()),
            "main title need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getPromoInfo()),
            "promo info need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getRemark()), "remark need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getLogo()), "logo need not blank");
        Assert.isTrue(StringUtils.isNotBlank(information.getPromoLogo()),
            "promo logo need not blank");
    }

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }


    public static void main(String[] args) {
        System.out.println(Integer.toHexString(10000));
        System.out.println("\u0000\u0000\u0000\u0000\u0000\u1001\u0080\u0000");
    }
}