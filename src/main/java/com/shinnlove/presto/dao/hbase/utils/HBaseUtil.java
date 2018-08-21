/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.dao.hbase.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

/**
 * @author shinnlove.jinsheng
 * @version $Id: HBaseUtil.java, v 0.1 2018-08-21 下午5:27 shinnlove.jinsheng Exp $$
 */
public class HBaseUtil {

    private static Configuration conf = null;
    private static Connection    conn = null;

    /**
     * 获取全局唯一的Configuration实例
     *
     * @return
     */
    public static synchronized Configuration getConfiguration() {
        if (conf == null) {
            // 此处从配置文件读取配置信息，配置文件在classpath下的hbase-site.xml。
            conf = HBaseConfiguration.create();
        }
        return conf;
    }

    /**
     * 获取全局唯一的Connection实例
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws IOException {
        if (conn == null) {
            synchronized (HBaseUtil.class) {
                if (conn == null) {
                    /*
                     * * 创建一个Connection
                     */
                    //第一种方式：通过配置文件
                    Configuration configuration = getConfiguration();
                    //第二种方式：代码中指定
                    //Configuration configuration = new Configuration();
                    //configuration.set("bdp.hbase.erp", "yangyuan64");//你的erp
                    //configuration.set("bdp.hbase.instance.name", "SL1000000003014");//申请的实例名称
                    //configuration.set("bdp.hbase.accesskey", "MZYH5UIKEY3BU7CNB5FWLS2OTA");//实例对应的accesskey，请妥善保管你的AccessKey
                    conn = ConnectionFactory.createConnection(configuration);
                }
            }
        }
        return conn;
    }

}