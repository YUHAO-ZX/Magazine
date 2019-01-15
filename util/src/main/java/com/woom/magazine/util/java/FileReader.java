/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.java;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 文件按行读取器
 *
 * @author yuhao.zx
 * @version $Id: FileReader.java, v 0.1 2018年12月25日 5:34 PM yuhao.zx Exp $
 */
public class FileReader {

    public static void main(String[] args) throws IOException {

        //Stream<String> lines = Files.lines(Paths.get("/Users/yuhao.zx/Desktop/temp/1.txt"),
        //    Charset.defaultCharset());
        //
        //lines.forEach(s -> System.out.println(s));

        List<String> sqls = new ArrayList<>();
        String template = "insert into index_test(field1,field2,field3,name) values('%s','%s','%s','%s');";
        for(int i=0;i<100000;i++){
            String temp = String.valueOf((int)(100000*Math.random()));
            sqls.add(String.format(template,temp,temp+"1",temp+"2",temp+"3"));
        }
        Files.write(Paths.get("/Users/yuhao.zx/Desktop/temp/1.sql"),sqls);

    }
}