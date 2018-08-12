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

    /**
     * count模型数量
     *
     * @return
     */
    @RequestMapping(value = "/count", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject countUser() {
        int result = userQueryService.countUser();
        return ResponseUtil.success(result);
    }

    /**
     * 新增模型
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject addUser(User user) {
        int result = userQueryService.addUser(user);
        return ResponseUtil.success(result);
    }

    /**
     * 根据ID删除模型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject delete(int id) {
        int result = userQueryService.deleteUser(id);
        return ResponseUtil.success(result);
    }

    /**
     * 查询所有模型
     *
     * @return
     */
    @RequestMapping(value = "/all", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject queryAll() {
        List<User> userList = userQueryService.getAllUser();
        return ResponseUtil.success(userList);
    }

    /**
     * 根据ID查询模型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/id", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject queryId(String id) {
        User user = userQueryService.getUserById(Integer.valueOf(id));
        return ResponseUtil.success(user);
    }

    /**
     * 根据模型属性查询模型
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/name", method = { RequestMethod.GET, RequestMethod.POST })
    public JSONObject queryName(String name) {
        List<User> userList = userQueryService.getUserListByName(name);
        return ResponseUtil.success(userList);
    }

}