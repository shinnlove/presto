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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    /** presto SQL执行服务 */
    @Autowired
    private SQLExecuteService sqlExecuteService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public int sqlQuery() {

        List<Map<String, Object>> list = sqlExecuteService.querySQLByID(1);
        return list.size();
        }

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public void sqlExecute(String sql) {
        sqlExecuteService.executeSQL(sql);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "hello, this is controller.";
    }

}