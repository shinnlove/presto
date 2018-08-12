/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.util.asserts;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.shinnlove.presto.util.code.SystemResultCode;
import com.shinnlove.presto.util.exception.SystemException;

/**
 * 校参通用类。
 *
 * 可接收错误码，抛出{@link SystemException}
 *
 * @author shinnlove.jinsheng
 * @version $Id: Assert.java, v 0.1 2018-08-12 下午6:31 shinnlove.jinsheng Exp $$
 */
public class Assert extends org.springframework.util.Assert {

    /**
     * 字符串判空。
     *
     * @param str
     * @throws IllegalArgumentException
     */
    public static void notBlank(String str) throws IllegalArgumentException {
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("参数不能为空");
        }
    }

    /**
     * 字符串判空。
     *
     * @param str
     * @param resultCode
     * @throws SystemException
     */
    public static void notBlank(String str, SystemResultCode resultCode) throws SystemException {
        if (!StringUtils.hasText(str)) {
            throw new SystemException(resultCode, "参数不能为空");
        }
    }

    /**
     * 字符串判不同。
     *
     * @param origin
     * @param compare
     * @throws IllegalArgumentException
     */
    public static void strNotEquals(String origin, String compare) throws IllegalArgumentException {
        if (origin.equalsIgnoreCase(compare)) {
            throw new IllegalArgumentException("参数不能为指定的目标值");
        }
    }

    /**
     * 字符串判不同。
     *
     * @param origin
     * @param compare
     * @param resultCode
     * @throws SystemException
     */
    public static void strNotEquals(String origin, String compare, SystemResultCode resultCode)
                                                                                               throws SystemException {
        if (origin.equalsIgnoreCase(compare)) {
            throw new SystemException(resultCode, "参数不能为指定的目标值");
        }
    }

    /**
     * 列表元素不能为空。
     *
     * @param list
     * @throws IllegalArgumentException
     */
    public static void listNotEmpty(List list) throws IllegalArgumentException {
        if (CollectionUtils.isEmpty(list)) {
            throw new IllegalArgumentException("集合元素不能为空");
        }
    }

    /**
     * 列表元素不能为空。
     *
     * @param list
     * @param resultCode
     * @throws SystemException
     */
    public static void listNotEmpty(List list, SystemResultCode resultCode) throws SystemException {
        if (CollectionUtils.isEmpty(list)) {
            throw new SystemException(resultCode, "集合元素不能为空");
        }
    }

    /**
     * 整形必须大于多少判断。
     *
     * @param self
     * @param compare
     * @throws IllegalArgumentException
     */
    public static void integerBiggerThan(int self, int compare) throws IllegalArgumentException {
        if (self <= compare) {
            throw new IllegalArgumentException("自身值小于等于目标值");
        }
    }

    /**
     * 整形必须大于多少判断。
     *
     * @param self
     * @param compare
     * @param resultCode
     * @throws SystemException
     */
    public static void integerBiggerThan(int self, int compare, SystemResultCode resultCode)
                                                                                            throws SystemException {
        if (self <= compare) {
            throw new SystemException(resultCode, "自身值小于等于目标值");
        }
    }

}