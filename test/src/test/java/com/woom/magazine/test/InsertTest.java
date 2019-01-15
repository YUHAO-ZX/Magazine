/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.woom.magazine.test;

/**
 *
 * @author yuhao.zx
 * @version $Id: InsertTest.java, v 0.1 2019年01月11日 3:13 PM yuhao.zx Exp $
 */
public class InsertTest {

    public static void main(String[] args) {
        String template = "INSERT INTO index_test(field1, field2, field3, name) VALUES ('name%s', 'name%s', 'name%s', 'gggggg%s');";

        for (int i = 0; i < 100; i++) {
            System.out.println(String.format(template,String.valueOf(i),String.valueOf(i+1),String.valueOf(i+2),String.valueOf(i)));
        }
    }
}