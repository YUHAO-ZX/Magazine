/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import java.util.Map;

/**
 *
 * @author yuhao.zx
 * @version $Id: Schema.java, v 0.1 2018年10月19日 下午9:05 yuhao.zx Exp $
 */
public class Schema {

    Map<String,ItemDescribe> itemDescribeMap;

    /**
     * Getter method for property <tt>itemDescribeMap</tt>.
     *
     * @return property value of itemDescribeMap
     */
    public Map<String, ItemDescribe> getItemDescribeMap() {
        return itemDescribeMap;
    }

    /**
     * Setter method for property <tt>itemDescribeMap</tt>.
     *
     * @param itemDescribeMap  value to be assigned to property itemDescribeMap
     */
    public void setItemDescribeMap(Map<String, ItemDescribe> itemDescribeMap) {
        this.itemDescribeMap = itemDescribeMap;
    }
}