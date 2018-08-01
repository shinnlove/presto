/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.service;

import com.shinnlove.presto.util.exception.SystemException;

/**
 * 使用JDBC执行sql的服务接口。
 *
 * @author shinnlove.jinsheng
 * @version $Id: SQLExecuteService.java, v 0.1 2018-07-31 下午11:25 shinnlove.jinsheng Exp $$
 */
public interface SQLExecuteService {

    /**
     * 查询sql。
     * (1)、count(*)返回Integer.class(int)
     * (2)、select * 返回具体的模型对象但是这边不知道具体类型，统统用object处理。
     *
     * @param sql
     * @return
     */
    Object querySQL(String sql) throws SystemException;

    /**
     * 执行sql。
     * 
     * @param sql
     * @return
     */
    @Deprecated
    int executeSQL(String sql) throws SystemException;

}
