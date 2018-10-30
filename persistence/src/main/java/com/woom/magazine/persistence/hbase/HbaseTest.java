/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase;

import com.google.protobuf.ServiceException;
import org.apache.commons.lang.time.StopWatch;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableFactory;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.mortbay.util.ajax.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://hbase.apache.org/book.html#getting_started
 *
 * @author yuhao.zx
 * @version $Id: HbaseConnector.java, v 0.1 2018年10月30日 3:55 PM yuhao.zx Exp $
 */
public class HbaseTest {

    public static void main(String[] args) throws IOException {
        //testAddColumns();
        //testBatchInput();
        testScan();
    }

    public static final byte[] CF = "cf".getBytes();
    public static final byte[] ATTR = "attr".getBytes();
    public static void testScan() throws IOException {
        List<Map<String,String>> rs = HbaseGetter.scan("test","cf","name","client_test_row");
        System.out.println(JSON.toString(rs));
    }

    public static void testAddColumns() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<String> manyColumn = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            manyColumn.add(new StringBuilder().append("client_test_row").append(",column").append(i).append(",zhangxin").append(i).toString());
        }

        System.out.println("cost-" + stopWatch.getTime());
        HbasePutter.put("test", "cf", manyColumn);
        System.out.println("cost-" + stopWatch.getTime());

        Map<String, String> resultMap = HbaseGetter.get("test", "client_test_row", "cf");
        System.out.println(JSON.toString(resultMap) + "-" + stopWatch.getTime());
    }

    public static void testBatchInput() throws IOException {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();


        Map<String, String> resultMap = HbaseGetter.get("test", "row1", "cf");

        System.out.println(JSON.toString(resultMap) + "-" + stopWatch.getTime());

        //for(int i=0;i<10000;i++){
        //    HbasePutter.put("test","client_test_row"+i,"cf","name","zhangxin"+i);
        //    HbasePutter.put("test","client_test_row"+i,"cf","address","四川广元"+i);
        //    if(i % 100 == 0){
        //        System.out.println("cost-"+stopWatch.getTime());
        //    }
        //}

        List<String> requestName = new ArrayList<>();
        List<String> requestAddress = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            requestName.add(new StringBuilder().append("client_test_row").append(i)
                .append(",name,zhangxin").append(i).toString());
            requestName.add(new StringBuilder().append("client_test_row").append(i)
                .append(",address,四川广元").append(i).toString());
        }

        System.out.println("cost-" + stopWatch.getTime());
        HbasePutter.put("test", "cf", requestName);
        System.out.println("cost-" + stopWatch.getTime());
        HbasePutter.put("test", "cf", requestAddress);

        System.out.println("cost-" + stopWatch.getTime());
        for (int i = 0; i < 10000; i++) {
            resultMap = HbaseGetter.get("test", "client_test_row" + i, "cf");
            System.out.println(JSON.toString(resultMap) + "-" + stopWatch.getTime());
        }

    }
}