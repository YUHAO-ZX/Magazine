/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.concurrent.future;

/**
 *
 * @author yuhao.zx
 * @version $Id: ConcurrentCallBack.java, v 0.1 2018年10月13日 下午1:22 yuhao.zx Exp $
 */
public interface ConcurrentCallBack<T> {
    /**
     * 执行
     *
     * @return 结果类型
     */
    T process();
}