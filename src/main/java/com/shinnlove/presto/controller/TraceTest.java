/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.controller;

import java.util.Random;

/**
 * @author shinnlove.jinsheng
 * @version $Id: TraceTest.java, v 0.1 2018-08-11 下午9:34 shinnlove.jinsheng Exp $$
 */
public class TraceTest {

    private static int calculate(int a, int b) {
        Random random = new Random();
        int ms = random.nextInt(100);
        try {
            Thread.sleep(ms * 10);
        } catch (InterruptedException e) {

        }
        return a + b;
    }

    public static void main(String[] args) {
        int a = 1, b = 2;
        while (true) {
            a = calculate(a, b);
            System.out.println(a);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}