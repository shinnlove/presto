/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.dao.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTableInterfaceFactory;

/**
 * @author shinnlove.jinsheng
 * @version $Id: SpringTableFactory.java, v 0.1 2018-08-21 下午5:33 shinnlove.jinsheng Exp $$
 */
public class SpringTableFactory implements HTableInterfaceFactory {

    @Override
    public HTableInterface createHTableInterface(Configuration configuration, byte[] bytes) {
        try {
            return (HTableInterface) HBaseConnectionUtil.getConnection(configuration).getTable(
                TableName.valueOf(bytes));
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    @Override
    public void releaseHTableInterface(HTableInterface hTableInterface) throws IOException {
        hTableInterface.close();
    }
}