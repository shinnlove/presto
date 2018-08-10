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

import com.shinnlove.presto.controller.advice.CommonExceptionAdvice;
import com.shinnlove.presto.model.User;
import com.shinnlove.presto.service.UserQueryService;
import com.shinnlove.presto.util.exception.SystemException;

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

    @RequestMapping(value = "/id", method = { RequestMethod.GET, RequestMethod.POST })
    public User sqlQuery(String id) {
        return userQueryService.getUserById(Integer.valueOf(id));
    }

    @RequestMapping(value = "/name", method = { RequestMethod.GET, RequestMethod.POST })
    public List<User> sqlExecute(String name) {
        return userQueryService.getUserListByName(name);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello() {
        return "Hello, this is rest controller.";
    }

}