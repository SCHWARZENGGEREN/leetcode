package com.example.leetcode.demoTest;

import java.util.Date;

/**
 * @Auther: Rxh
 * @Date: 2021/9/23 14:27
 * @Description:
 */
public class GCTest {

    public static void main(String[] args) {
        GCTest gcTest = new GCTest();

        gcTest = null;

        System.gc();
        while (true){}
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(new Date()+": i died...");
    }
}
