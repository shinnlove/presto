/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.dao.hbase;

import org.apache.hadoop.conf.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * 自定义Hbase模板。
 *
 * @author shinnlove.jinsheng
 * @version $Id: MyHbaseTemplate.java, v 0.1 2018-08-21 下午5:32 shinnlove.jinsheng Exp $$
 */
public class MyHbaseTemplate extends HbaseTemplate {

    public MyHbaseTemplate(Configuration configuration) {
        super(configuration);
        this.setTableFactory(new SpringTableFactory());
    }

}