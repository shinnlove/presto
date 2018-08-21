/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.dao.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * Hbase连接工具类。
 *
 * @author shinnlove.jinsheng
 * @version $Id: HBaseConnectionUtil.java, v 0.1 2018-08-21 下午5:19 shinnlove.jinsheng Exp $$
 */
public class HBaseConnectionUtil {

    private static Connection connection;

    public static Connection getConnection(Configuration configuration) {

        if (connection == null) {
            synchronized (HBaseConnectionUtil.class) {
                try {
                    connection = ConnectionFactory.createConnection(configuration);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return connection;

    }

}