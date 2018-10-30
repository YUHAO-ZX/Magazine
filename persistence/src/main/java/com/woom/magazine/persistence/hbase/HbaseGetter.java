/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase;

import org.apache.commons.collections.map.HashedMap;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import sun.jvm.hotspot.utilities.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * hbase getter
 *
 * @author yuhao.zx
 * @version $Id: HbaseGetter.java, v 0.1 2018年10月30日 5:27 PM yuhao.zx Exp $
 */
public class HbaseGetter {

    /**
     * 获取数据
     *
     * @param tableName 表名称
     * @param rowKey rowkey
     * @param family 列簇
     * @return key-v
     * @throws IOException
     */
    public static Map<String, String> get(String tableName, String rowKey,
                                          String family) throws IOException {

        HTableInterface table = HbaseTable.getTable(tableName);
        Assert.that(table != null, "table not exist");
        Get get = new Get(Bytes.toBytes(rowKey));
        Result rs = table.get(get);
        return convert(rs.getFamilyMap(Bytes.toBytes(family)));
    }

    /**
     * 扫描数据（查询表某列全部数据）
     * 
     * @param tableName 表名
     * @param family 列簇
     * @param column 某列
     * @param preFixFilter rowkey 前缀过滤
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> scan(String tableName, String family, String column,
                                                 String preFixFilter) throws IOException {
        HTableInterface table = HbaseTable.getTable(tableName);

        Assert.that(table != null, "table not exist");

        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(family), Bytes.toBytes(column));

        //row key pre fix
        scan.setRowPrefixFilter(Bytes.toBytes(preFixFilter));
        ResultScanner rs = table.getScanner(scan);
        List<Map<String, String>> rsList = new ArrayList<>();
        try {

            for (Result r = rs.next(); r != null; r = rs.next()) {
                rsList.add(convert(r.getFamilyMap(Bytes.toBytes(family))));
            }
        } finally {
            rs.close(); // always close the ResultScanner!
        }

        return rsList;
    }

    /**
     * Hbase original to map
     * 
     * @param rsMap
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Map<String, String> convert(NavigableMap<byte[], byte[]> rsMap) throws UnsupportedEncodingException {
        Map<String, String> rMap = new HashedMap();

        for (Map.Entry<byte[], byte[]> entry : rsMap.entrySet()) {
            byte[] keyBytes = entry.getKey();
            byte[] valueBytes = entry.getValue();
            rMap.put(new String(keyBytes, "UTF-8"), new String(valueBytes, "UTF-8"));
        }
        return rMap;
    }
}