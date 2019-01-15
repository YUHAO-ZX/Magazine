/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.util.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *
 * @author yuhao.zx
 * @version $Id: AnnotationAspect.java, v 0.1 2018年11月12日 3:52 PM yuhao.zx Exp $
 */
@Component
@Aspect
public class AnnotationAspect {

    @Around("@annotation(com.woom.magazine.util.aspect.TestAnnotation) && @annotation(testAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, TestAnnotation testAnnotation) throws Throwable {
        System.out.println("in annotation");
        return joinPoint.proceed(joinPoint.getArgs());
    }
}