/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shinnlove.presto.controller.advice.CommonExceptionAdvice;
import com.shinnlove.presto.model.User;
import com.shinnlove.presto.service.UserQueryService;
import com.shinnlove.presto.util.exception.SystemException;
import com.shinnlove.presto.util.tools.ResponseUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 执行`presto`SQL查询请求的控制器。
 *
 * 这里没有try...catch，@see {@link CommonExceptionAdvice}；
 * 整套系统的错误机制，@see {@link SystemException}。
 *
 * @author shinnlove.jinsheng
 * @version $Id: PrestoQueryController.java, v 0.1 2018-07-31 下午11:43 shinnlove.jinsheng Exp $$
 * @since spring 4.x -> `@RestController` = `@Controller` +`@RequestBody`
 */
@RestController
@RequestMapping(value = "/query")
public class PrestoQueryController {

    /** presto SQL执行服务 */
    @Autowired
    private UserQueryService userQueryService;

<<<<<<< HEAD
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public int sqlQuery() {

        List<Map<String, Object>> list = sqlExecuteService.querySQLByID(1);
        return list.size();
        }
=======
    @RequestMapping(value = "/id", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject sqlQuery(String id) {
        User user = userQueryService.getUserById(Integer.valueOf(id));
        return ResponseUtil.success(user);
    }
>>>>>>> 199138f9ae576b26f369ce832b2aeeb7f0d841ed

    @RequestMapping(value = "/name", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject sqlExecute(String name) {
        List<User> userList = userQueryService.getUserListByName(name);
        return ResponseUtil.success(userList);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "Hello, this is rest controller.";
    }

}