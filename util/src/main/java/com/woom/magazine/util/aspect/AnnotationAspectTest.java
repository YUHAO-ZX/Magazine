/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.aspect;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author yuhao.zx
 * @version $Id: AnnotationAspectTest.java, v 0.1 2018年11月12日 4:00 PM yuhao.zx Exp $
 */
@Component
public class AnnotationAspectTest {

    @TestAnnotation
    public void test() {
        System.out.println("in test");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/aspect.xml");
        AnnotationAspectTest test  = context.getBean("annotationAspectTest",AnnotationAspectTest.class);
        test.test();
    }
}