package com.example.leetcode.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Score {
    S time();

    S memory();

    /**
     * SSS:99-100
     * SS:90-99
     * S:75-90
     * A:50-75
     * B:25-50
     * C:10-25
     * D:0-10
     *
     *
     * [2021-11-4]
     * SSS:99-100
     * SS:90-99
     * S:80-90
     * A:60-80
     * B:40-60
     * C:20-40
     * D:0-20
     */

    enum S {
        SSS,
        SS,
        S,
        A,
        B,
        C,
        D,

        NONE,//未通过
        OT,//over time 超时
        OOM,// out of memory 内存溢出
        ;
    }
}
