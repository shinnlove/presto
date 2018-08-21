/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.dao.hbase;

import java.util.Iterator;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;

/**
 * 自定义配置。
 *
 * @author shinnlove.jinsheng
 * @version $Id: MyConfiguration.java, v 0.1 2018-08-21 下午5:02 shinnlove.jinsheng Exp $$
 */
public class MyConfiguration extends Configuration {

    public MyConfiguration(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            this.set(entry.getKey(), entry.getValue());
        }
    }

}