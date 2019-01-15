/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.persistence.hbase.schema;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.woom.magazine.persistence.hbase.schema.Field.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author yuhao.zx
 * @version $Id: SchemaGenerator.java, v 0.1 2018年10月30日 11:14 PM yuhao.zx Exp $
 */
public class SchemaGenerator {

    public static Schema readByProperties(String schemaName) throws IOException {
        Properties properties = new Properties();
        InputStream in = SchemaGenerator.class.getClassLoader().getResourceAsStream("schemas/"+schemaName+".properties");
        properties.load(in);
        String schemaFieldList = properties.getProperty("schemaFieldList");
        String schemaId = properties.getProperty("schemaId");

        List<Field> fieldList = JSONArray.parseArray(schemaFieldList,Field.class);
        Schema schema = new Schema(fieldList);
        schema.setSchemaId(schemaId);
        return schema;
    }

    public static void main(String[] args) {
        List<Field> fieldList = new ArrayList<>();
        Field logo = new Field();
        logo.setType(Type.STRING);
        logo.setFieldName("logo");

        Field firstTitle = new Field();
        firstTitle.setType(Type.STRING);
        firstTitle.setFieldName("肯德基");

        Field pro = new Field();
        pro.setType(Type.INT);
        pro.setFieldName("权重");

        fieldList.add(pro);
        fieldList.add(logo);
        fieldList.add(firstTitle);
        Schema schema = new Schema(fieldList);

        System.out.println(JSON.toJSONString(schema));
    }
}