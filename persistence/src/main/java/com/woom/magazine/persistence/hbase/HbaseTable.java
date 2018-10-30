/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableFactory;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author yuhao.zx
 * @version $Id: HbaseTable.java, v 0.1 2018年10月30日 5:20 PM yuhao.zx Exp $
 */
public class HbaseTable {

    static Configuration config = null;
    static {
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "11.166.38.201");
        config.set("hbase.zookeeper.property.clientPort", "2181");


        try {
            HBaseAdmin.checkHBaseAvailable(config);
        }catch (MasterNotRunningException e){
            System.out.printf("Hbase is not running");
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Map<String ,HTableInterface> tables = new ConcurrentHashMap();

    public static HTableInterface getTable(String tableName){
        if(tables.get(tableName) != null){
            return tables.get(tableName);
        }

        HTableFactory factory = new HTableFactory();
        HTableInterface hTableInterface = factory.createHTableInterface(config, Bytes.toBytes(tableName));
        tables.put(tableName,hTableInterface);

        return hTableInterface;
    }

    public static HBaseAdmin getAdmin() throws IOException {
        return new HBaseAdmin(config);
    }
}