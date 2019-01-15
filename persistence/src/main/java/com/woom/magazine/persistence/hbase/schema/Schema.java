/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase.schema;

import java.util.List;

/**
 * schema模式
 *
 * @author yuhao.zx
 * @version $Id: Schema.java, v 0.1 2018年10月30日 11:06 PM yuhao.zx Exp $
 */
public class Schema {


    public Schema(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    private List<Field> fieldList;

    private String schemaId;

    /**
     * Getter method for property <tt>schemaId</tt>.
     *
     * @return property value of schemaId
     */
    public String getSchemaId() {
        return schemaId;
    }

    /**
     * Setter method for property <tt>schemaId</tt>.
     *
     * @param schemaId  value to be assigned to property schemaId
     */
    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    /**
     * Getter method for property <tt>fieldList</tt>.
     *
     * @return property value of fieldList
     */
    public List<Field> getFieldList() {
        return fieldList;
    }

    /**
     * Setter method for property <tt>fieldList</tt>.
     *
     * @param fieldList  value to be assigned to property fieldList
     */
    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }
}