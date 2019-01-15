///**
// * Alipay.com Inc.
// * Copyright (c) 2004-2018 All Rights Reserved.
// */
//package com.woom.magaine.activiti.test;
//
//import org.activiti.api.model.shared.model.VariableInstance;
//import org.activiti.api.process.model.ProcessDefinition;
//import org.activiti.api.process.model.ProcessInstance;
//import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
//import org.activiti.api.process.runtime.ProcessRuntime;
//import org.activiti.api.process.runtime.connector.Connector;
//import org.activiti.api.runtime.shared.query.Page;
//import org.activiti.api.runtime.shared.query.Pageable;
//import org.activiti.api.task.model.Task;
//import org.activiti.api.task.model.builders.TaskPayloadBuilder;
//import org.activiti.api.task.runtime.TaskRuntime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// *
// * @author yuhao.zx
// * @version $Id: TestApplication.java, v 0.1 2018年10月31日 10:43 PM yuhao.zx Exp $
// */
//@SpringBootApplication
//@EnableScheduling
//public class TestApplication implements CommandLineRunner {
//
//    private Logger               logger = LoggerFactory.getLogger(TestApplication.class);
//
//    private final ProcessRuntime processRuntime;
//
//    private final TaskRuntime    taskRuntime;
//
//    private final SecurityUtil   securityUtil;
//
//    public TestApplication(ProcessRuntime processRuntime, TaskRuntime taskRuntime,
//                           SecurityUtil securityUtil) {
//        this.processRuntime = processRuntime;
//        this.taskRuntime = taskRuntime;
//        this.securityUtil = securityUtil;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(TestApplication.class, args);
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        securityUtil.logInAs("system");
//
//        Page<ProcessDefinition> processDefinitionPage = processRuntime
//            .processDefinitions(Pageable.of(0, 10));
//        logger.info("> Available Process definitions: " + processDefinitionPage.getTotalItems());
//        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
//            logger.info("\t > Process definition: " + pd);
//        }
//    }
//
//    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
//    public void processStartAudit() {
//        logger.info("> processStartAudit");
//        securityUtil.logInAs("system");
//        logger.info("> 用户发起审批.");
//
//        ProcessInstance processInstance = processRuntime
//            .start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1")
//                .withProcessInstanceName("Processing Content: test").build());
//    }
//
//    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
//    public void doAudit() {
//        securityUtil.logInAs("salaboy");
//
//        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
//        if (tasks.getTotalItems() > 0) {
//            for (Task t : tasks.getContent()) {
//
//                logger.info("> 开始审批.");
//                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(t.getId()).build());
//
//                int value = (int)(Math.random() * 10);
//                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(t.getId())
//                    .withVariable("ok", value > 5).build());
//            }
//
//        } else {
//            logger.info("> There are no task for me to work on.");
//        }
//    }
//
//    @Bean
//    public Connector okConnector() {
//        return integrationContext -> {
//            logger.info("----------ok");
//            //String contentToTag = (String) integrationContext.getInBoundVariables().get("content");
//            //integrationContext.addOutBoundVariable("content",
//            //        contentToTag);
//            //logger.info("Final Content: " + contentToTag);
//            return integrationContext;
//        };
//    }
//
//    @Bean
//    public Connector notOkConnector() {
//        return integrationContext -> {
//            logger.info("----------notok");
//            //String contentToDiscard = (String) integrationContext.getInBoundVariables().get("content");
//            //contentToDiscard += " :( ";
//            //integrationContext.addOutBoundVariable("content",
//            //        contentToDiscard);
//            //logger.info("Final Content: " + contentToDiscard);
//            return integrationContext;
//        };
//    }
//}