/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.controller;

import com.shinnlove.presto.util.log.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shinnlove.presto.service.SQLExecuteService;

/**
 * 执行`presto`SQL查询请求的控制器。
 *
 * @author shinnlove.jinsheng
 * @version $Id: PrestoQueryController.java, v 0.1 2018-07-31 下午11:43 shinnlove.jinsheng Exp $$
 * @since spring 4.x -> `@RestController` = `@Controller` +`@RequestBody`
 */
@RestController
@RequestMapping(value = "/sql")
public class PrestoQueryController {

    //    private static final Logger ERROR_LOG = LoggerFactory.getLogger("logger.error");

    //    private static final Logger TEST_LOG = LoggerFactory.getLogger("logger.test");

//    private static final Logger PACKAGE_LOG = LoggerFactory.getLogger(PrestoQueryController.class);

    private static final Logger logger = LoggerFactory.getLogger("org.shinnlove.presto");

    /** presto SQL执行服务 */
    @Autowired
    private SQLExecuteService   sqlExecuteService;

    @RequestMapping(value = "/query", method = { RequestMethod.GET, RequestMethod.POST })
    public void sqlQuery(String sql) {
        sqlExecuteService.querySQL(sql);
    }

    @RequestMapping(value = "/execute", method = { RequestMethod.GET, RequestMethod.POST })
    public void sqlExecute(String sql) {
        sqlExecuteService.executeSQL(sql);
    }

    @RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
    public String hello() {
        //        LoggerUtil.info(TEST_LOG, "你好");
        LoggerUtil.info(logger, "你好");
        //        LoggerUtil.warn(TEST_LOG, "警告");
        LoggerUtil.warn(logger, "警告");
        //        LoggerUtil.error(ERROR_LOG, new RuntimeException("自定义错误"),"错误");
        LoggerUtil.error(logger, new RuntimeException("自定义错误"),"错误");
        return "hello, this is controller.";
    }

}