/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.guava.hashcode;

/**
 *
 * @author yuhao.zx
 * @version $Id: AnyObject.java, v 0.1 2018年10月13日 下午12:14 yuhao.zx Exp $
 */
public class AnyObject {

    private String field1;

    private String field2;

    public AnyObject(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    /**
     * Getter method for property <tt>field1</tt>.
     *
     * @return property value of field1
     */
    public String getField1() {
        return field1;
    }

    /**
     * Setter method for property <tt>field1</tt>.
     *
     * @param field1  value to be assigned to property field1
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * Getter method for property <tt>field2</tt>.
     *
     * @return property value of field2
     */
    public String getField2() {
        return field2;
    }

    /**
     * Setter method for property <tt>field2</tt>.
     *
     * @param field2  value to be assigned to property field2
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }
}