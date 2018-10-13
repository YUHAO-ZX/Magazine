/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.guava.hashcode;

import com.sun.xml.internal.xsom.impl.WildcardImpl.Any;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EqualsBuilder
 * --方便多对象之间的对比
 *
 *
 *
 * @author yuhao.zx
 * @version $Id: HashCodeBuilderTest.java, v 0.1 2018年10月13日 下午12:09 yuhao.zx Exp $
 */
public class HashCodeBuilderTest {

    @Test
    public void testEquals(){

        AnyObject anyObject = new AnyObject("1","2");

        HashCodeBuilderObject builderObject = new HashCodeBuilderObject();
        builderObject.setName("o1");
        builderObject.setObject(anyObject);

        HashCodeBuilderObject builderObject2 = new HashCodeBuilderObject();
        builderObject2.setName("o2");
        builderObject2.setObject(anyObject);

        System.out.println(builderObject == builderObject2);

        System.out.println(builderObject.equals(builderObject2));

        Map<HashCodeBuilderObject,String> contianer = new HashMap();
        contianer.put(builderObject,"1");
        contianer.put(builderObject2,"2");

        System.out.println(contianer.get(builderObject));
        System.out.println(contianer.get(builderObject2));
    }

    @Test
    public void testReflectionEquals(){
        AnyObject anyObject = new AnyObject("1","2");
        AnyObject anyObject2 = new AnyObject("1","2");

        boolean isEqual = EqualsBuilder.reflectionEquals(anyObject, anyObject2);
        System.out.println(isEqual);
    }

    /**
     * EqualsBuilder,方便多对象之间的对比
     */
    @Test
    public void testMutiEquals(){

        AnyObject anyObject = new AnyObject("1","2");
        AnyObject anyObject2 = new AnyObject("1","2");

        System.out.println(new EqualsBuilder().reflectionAppend(anyObject,anyObject2).append("1","1").append(true,true).append(2,2).isEquals());

        System.out.println(new EqualsBuilder().reflectionAppend(anyObject,anyObject2).append("1","1").append(true,true).append(2,3).isEquals());
    }

    /**
     * 不能array reflection equal
     */
    @Test
    public void testArrayEquals(){
        List<AnyObject> list1 = new ArrayList();
        list1.add(new AnyObject("1","2"));
        list1.add(new AnyObject("3","4"));

        List<AnyObject> list2 = new ArrayList();
        list2.add(new AnyObject("1","2"));
        list2.add(new AnyObject("3","4"));

        System.out.println(new EqualsBuilder().append(list1,list2).isEquals());
        System.out.println(new EqualsBuilder().append(list1.toArray(),list2.toArray()).isEquals());
        System.out.println(new EqualsBuilder().reflectionAppend(list1.toArray(),list2.toArray()).isEquals());

    }
}