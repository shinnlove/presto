/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.service;

import java.util.List;

import com.shinnlove.presto.model.User;

/**
 * 这是领域模型的查询接口。
 *
 * 示例：这里领域模型是用户User，其他如查询商品推荐信息，就是`ProductInfoQueryService`等等等...
 * 领域模型肯定有CURD，因此sql语句自己定义，参数要靠外界传入。
 * 从来没有什么系统说直接传入sql执行的，无论是外网、还是内网系统交互，无论是rest还是webservice，都是K/V来做的。
 *
 * @author shinnlove.jinsheng
 * @version $Id: UserQueryService.java, v 0.1 2018-08-10 下午3:12 shinnlove.jinsheng Exp $$
 */
public interface UserQueryService {

    /**
     * 根据id查询单个用户。
     *
     * @return
     */
    User getUserById(int id);

    /**
     * 根据姓名查询单个用户。
     *
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 给定姓名获取用户列表，可能存在重复的用户，这里示例查询多个用户。
     *
     * @param name
     * @return
     */
    List<User> getUserListByName(String name);

}
