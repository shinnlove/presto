/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shinnlove.presto.service.SQLExecuteService;
import com.shinnlove.presto.util.code.SystemResultCode;
import com.shinnlove.presto.util.exception.SystemException;
import com.shinnlove.presto.util.log.LoggerUtil;

/**
 * 使用JDBC执行sql的服务接口实现类。
 *
 * @author shinnlove.jinsheng
 * @version $Id: SQLExecuteServiceImpl.java, v 0.1 2018-07-31 下午11:28 shinnlove.jinsheng Exp $$
 */
@Service(value = "sqlExecuteService")
public class SQLExecuteServiceImpl implements SQLExecuteService {

    /** service层日志类 */
    private static Logger logger = LoggerFactory.getLogger(SQLExecuteServiceImpl.class);

    /** spring-jdbc集成 */
    @Autowired
    private JdbcTemplate  jdbcTemplate;

    /**
     * @see com.shinnlove.presto.service.SQLExecuteService#querySQL(String)
     */
    @Override
    public Object querySQL(String sql) throws SystemException {
        Object result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, Object.class);
        } catch (DataAccessException e) {
            LoggerUtil.error(logger, e, "SQL语句查询出错，原因是：", e.getMessage());
            throw new SystemException(SystemResultCode.DB_QUERY_ERROR, e);
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "系统错误，原因是：", e.getMessage());
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, e);
        }
        return result;
    }

    /**
     * @see com.shinnlove.presto.service.SQLExecuteService#executeSQL(String) 
     */
    @Override
    public int executeSQL(String sql) throws SystemException {
        int result = 0;
        try {
            // update如果是insert语句必须跟参数
            result = jdbcTemplate.update(sql);
        } catch (DataAccessException e) {
            LoggerUtil.error(logger, e, "SQL语句执行出错，原因是：", e.getMessage());
            throw new SystemException(SystemResultCode.DB_EXECUTE_ERROR, e);
        } catch (Exception e) {
            LoggerUtil.error(logger, e, "系统错误，原因是：", e.getMessage());
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, e);
        }
        return result;
    }

}