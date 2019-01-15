/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.guava.hashcode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 *
 *
 * @author yuhao.zx
 * @version $Id: HashCodeBuilderTest.java, v 0.1 2018年10月13日 上午11:57 yuhao.zx Exp $
 */
public class HashCodeBuilderObject {

    private AnyObject object;

    private String    name;

    /**
     * Setter method for property <tt>object</tt>.
     *
     * @param object  value to be assigned to property object
     */
    public void setObject(AnyObject object) {
        this.object = object;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name  value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof HashCodeBuilderObject) {
            HashCodeBuilderObject that = (HashCodeBuilderObject) o;
            return new EqualsBuilder().append(object, that.getObject()).isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(object).toHashCode();
    }

    public AnyObject getObject() {
        return object;
    }
}