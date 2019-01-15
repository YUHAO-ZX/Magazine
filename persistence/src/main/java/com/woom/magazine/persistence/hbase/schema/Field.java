/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase.schema;

/**
 * 字段
 *
 * @author yuhao.zx
 * @version $Id: Field.java, v 0.1 2018年10月30日 11:06 PM yuhao.zx Exp $
 */
public class Field {

    private Type   type;

    private String fieldName;

    /**
     * Getter method for property <tt>fieldName</tt>.
     *
     * @return property value of fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Setter method for property <tt>fieldName</tt>.
     *
     * @param fieldName  value to be assigned to property fieldName
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public Type getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type  value to be assigned to property type
     */
    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
                      INT, STRING
    }
}