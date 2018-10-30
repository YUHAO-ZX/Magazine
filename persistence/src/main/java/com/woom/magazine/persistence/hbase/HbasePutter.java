/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yuhao.zx
 * @version $Id: HbasePuter.java, v 0.1 2018年10月30日 6:36 PM yuhao.zx Exp $
 */
public class HbasePutter {
    public static void put(String tableName,String rowKey,String family,String column,String value) throws IOException {

        HTableInterface table = HbaseTable.getTable(tableName);

        // Row1 => Family1:Qualifier1, Family1:Qualifier2
        Put p = new Put(Bytes.toBytes(rowKey));
        p.add(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
        table.put(p);


        // Row3 => Family1:Qualifier1, Family2:Qualifier3
        //p = new Put(row3);
        //p.add(family1, qualifier1, cellData);
        //p.add(family2, qualifier3, cellData);
        //table.put(p);
        //HbaseTable.getAdmin().disableTable();
        //admin.disableTable(table1);
        //try {
        //    //HColumnDescriptor desc = new HColumnDescriptor(Bytes.toBytes(family));
        //    //HbaseTable.getAdmin().addColumn(tableName, desc);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //} finally {
        //    //admin.enableTable(table1);
        //}
        //p("Done. ");
    }

    public static void put(String tableName,String family,List<String> columnAndValues) throws IOException {

        HTableInterface table = HbaseTable.getTable(tableName);

        // Row1 => Family1:Qualifier1, Family1:Qualifier2
        List<Put> puts = new ArrayList<>();
        for(String ele : columnAndValues){
            String[] cv = ele.split(",");
            Put p = new Put(Bytes.toBytes(cv[0]));

            p.add(Bytes.toBytes(family), Bytes.toBytes(cv[1]), Bytes.toBytes(cv[2]));

            puts.add(p);
        }
        table.put(puts);
    }
}