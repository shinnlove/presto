/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.controller;

import com.shinnlove.presto.util.log.LoggerUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shinnlove.jinsheng
 * @version $Id: PrestoQueryControllerTest.java, v 0.1 2018-08-01 下午12:33 shinnlove.jinsheng Exp $$
 */
public class PrestoQueryControllerTest {

    private static Logger logger = LoggerFactory.getLogger(PrestoQueryControllerTest.class);

    @Test
    public void test(){
        LoggerUtil.info(logger, "信息");
        LoggerUtil.warn(logger, "警告");
        LoggerUtil.error(logger, new RuntimeException("出错了"), "错误");
    }

}