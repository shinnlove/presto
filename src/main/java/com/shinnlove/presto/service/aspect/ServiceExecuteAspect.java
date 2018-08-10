/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.service.aspect;

import java.lang.reflect.Method;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shinnlove.presto.util.code.SystemResultCode;
import com.shinnlove.presto.util.exception.SystemException;
import com.shinnlove.presto.util.log.LoggerUtil;

/**
 * 服务层执行的切面。
 *
 * 特别注意：我不想在服务层使用try...catch，因此使用切面捕捉一切出来的错误。
 *
 * @author shinnlove.jinsheng
 * @version $Id: ServiceExecuteAspect.java, v 0.1 2018-08-10 下午4:30 shinnlove.jinsheng Exp $$
 */
@Aspect
public class ServiceExecuteAspect {

    /** service层日志类 */
    private static Logger logger = LoggerFactory.getLogger(ServiceExecuteAspect.class);

    /**
     * 切点
     */
    @Pointcut("execution(* com.shinnlove.presto.service.impl.*.*(..))")
    public void point() {
    }

    /**
     * 主数据接口调用环绕切面，处理各种异常统一抛出。
     *
     * @param jp
     * @return
     */
    @Around("point()")
    public Object remoteServiceAround(ProceedingJoinPoint jp) {
        long start = System.currentTimeMillis();

        // 全类名
        String fullPackageName = jp.getTarget().getClass().getName();

        // 方法签名和方法
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();

        // 方法名
        String methodName = method.getName();

        // 方法参数
        Object[] args = jp.getArgs();

        // 最终结果
        Object result = null;

        // 打印Service层公共日志
        LoggerUtil.info(
            logger,
            "【SAL服务调用】开始" + fullPackageName + "." + methodName + ":" + "[args="
                    + ToStringBuilder.reflectionToString(args, ToStringStyle.SHORT_PREFIX_STYLE)
                    + "]");

        // 成功标识
        String successFlag = "Y";

        try {

            // 推进
            result = jp.proceed();

        } catch (Exception e) {
            // 处理已检查异常
            successFlag = "N";
            LoggerUtil.error(logger, e, "【SAL服务调用】【" + methodName + "】服务调用中出现已检查异常。");
            throw new SystemException(SystemResultCode.SERVICE_INVOKE_ERROR, e, "未知异常，ex="
                                                                                + e.getMessage());
        } catch (Throwable t) {
            // 兜底
            successFlag = "N";
            LoggerUtil.error(logger, t, "【SAL服务调用】【" + methodName + "】系统遇到未知Throwable错误。");
            throw new SystemException(SystemResultCode.SYSTEM_ERROR, new RuntimeException(t),
                "未知异常，ex=" + t.getMessage());
        } finally {
            long end = System.currentTimeMillis();
            LoggerUtil.info(
                logger,
                assemblePrintMsg(fullPackageName, methodName, args, result, successFlag, end
                                                                                         - start));
        }

        return result;
    }

    /**
     * 组装接口调用打印日志。
     *
     * @return
     */
    protected String assemblePrintMsg(String fullPackageClassName, String methodName,
                                      Object[] args, Object result, String successFlag,
                                      long elapseTime) {
        StringBuffer sb = new StringBuffer(100);
        sb.append("service log result:[(").append(Thread.currentThread().getId()).append(",")
            .append(fullPackageClassName).append(".").append(methodName).append(",")
            .append(successFlag).append(",").append(elapseTime).append("ms)]");
        return sb.toString();
    }

}