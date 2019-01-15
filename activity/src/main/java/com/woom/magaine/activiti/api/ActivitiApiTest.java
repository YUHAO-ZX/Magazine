/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magaine.activiti.api;

import com.oracle.tools.packager.Log;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author yuhao.zx
 * @version $Id: ActivitiApiTest.java, v 0.1 2018年10月31日 11:48 PM yuhao.zx Exp $
 */
public class ActivitiApiTest {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("processes/VacationRequest.bpmn")
                .deploy();

        System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
        Log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());


    }


}