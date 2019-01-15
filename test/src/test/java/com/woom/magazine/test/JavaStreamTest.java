/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.test;

import com.alibaba.fastjson.JSON;
import com.woom.magazine.test.domain.Menu;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Stream;

//
import static java.util.stream.Collectors.toList;

/**
 *
 * @author yuhao.zx
 * @version $Id: JavaStreamTest.java, v 0.1 2018年12月25日 3:05 PM yuhao.zx Exp $
 */
public class JavaStreamTest {

    public static List<Menu> initDataSet() {
        List<Menu> menuList = new ArrayList<>();

        menuList.add(new Menu(100, "milk"));
        menuList.add(new Menu(100, "milk"));
        menuList.add(new Menu(4000, "巧克力"));
        menuList.add(new Menu(200, "胡萝卜"));
        menuList.add(new Menu(500, "牛肉"));

        return menuList;
    }

    @Test
    public void testStream() {
        List<Menu> menuList = initDataSet();

        long count = menuList.stream().filter(menu -> menu.getCalorie() < 300).distinct().count();

        System.out.println(count);

        List<Menu> menus = menuList.stream().filter(menu -> menu.getCalorie() < 300).distinct()
            .collect(toList());

    }

    @Test
    public void testMap() {

        List<Menu> menuList = initDataSet();
        List<String> dishNames = menuList.stream().map(Menu::getName).collect(toList());

        System.out.println(JSON.toJSONString(dishNames));

        //显示有几个字母
        //方法1
        List<Integer> dishNameLength = menuList.stream().map(Menu::getName).collect(toList())
            .stream().map(String::length).collect(toList());
        System.out.println(JSON.toJSONString(dishNameLength));

        ////方法2
        List<Integer> dishNameLength2 = menuList.stream().map(Menu::getName).map(String::length)
            .collect(toList());
        System.out.println(JSON.toJSONString(dishNameLength2));

        //这种方式无法分离出整个集合所有单词,见用例：testFlatMap()
        List<String> words = new ArrayList();
        words.add("Hello");
        words.add("world");

        List<String[]> chars = words.stream().map(o -> o.split("")).distinct().collect(toList());
        System.out.println(JSON.toJSONString(chars));
    }

    @Test
    public void testFlatMap() {
        //String[] arrayOfWords = {"Goodbye", "World"};
        //Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        List<String> words = new ArrayList();
        words.add("Hello");
        words.add("world");

        List<String> chars = words.stream().map(o -> o.split("")).flatMap(Arrays::stream).distinct()
            .collect(toList());

        System.out.println(JSON.toJSONString(chars));

    }

    /**
     * 获取笛卡尔数组
     */
    @Test
    public void testFlatMapDecare() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4, 5, 6, 7, 8, 9);
        List<int[]> pairs = numbers1.stream()
            .flatMap(i -> numbers2.stream().map(j -> new int[] { i, j })).collect(toList());

        System.out.println(JSON.toJSONString(pairs));

    }

    @Test
    public void testReduce1() {
        List<Menu> menuList = initDataSet();
        Integer count = menuList.stream().map(o -> 1).reduce(0,
            (integer, integer2) -> integer + integer2);
        System.out.println(count);

    }

    @Test
    public void testReduceParallel() {
        List<Menu> menuList = initDataSet();
        Integer count = menuList.parallelStream().map(o -> 1).reduce(0,
            (integer, integer2) -> integer + integer2);
        System.out.println(count);
    }

    /**
     * mapToLong 为了减少 自动装箱，拆箱导致的性能问题
     */
    @Test
    public void testMapToLong() {
        List<Menu> menuList = initDataSet();
        long rs = menuList.stream().mapToLong(Menu::getCalorie).sum();
        OptionalLong max = menuList.stream().mapToLong(Menu::getCalorie).max();

        System.out.println(rs);
        System.out.println(max.getAsLong());
    }

    @Test
    public void testFileStream() throws IOException {

        Stream<String> lines = Files.lines(Paths.get("/Users/yuhao.zx/Desktop/temp/1.txt"),
            Charset.defaultCharset());

        lines.map(o -> o.split(" ")).flatMap(Arrays::stream).distinct()
            .forEach(o -> System.out.println(o));
    }

    @Test
    public void testUnBoundaryData() {
        Stream.iterate(0, n -> n + 2).limit(1000).forEach(System.out::println);
    }

    @Test
    public void testMathRandom() {
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }
}