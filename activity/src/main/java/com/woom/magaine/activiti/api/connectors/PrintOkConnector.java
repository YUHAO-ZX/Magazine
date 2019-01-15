/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magaine.activiti.api.connectors;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 *
 * @author yuhao.zx
 * @version $Id: PrintOkConnector.java, v 0.1 2018年11月02日 2:38 PM yuhao.zx Exp $
 */
public class PrintOkConnector implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("ok");
    }
}