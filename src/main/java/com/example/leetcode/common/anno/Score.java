package com.example.leetcode.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Score {
    S time();
    S memory() default S.NONE;

    WAY way() default WAY.SELF;
    /**
     * FULL:满昏!
     * SSS:99-99.999
     * SS:90-99
     * S:80-90
     * A:60-80
     * B:40-60
     * C:20-40
     * D:5-20
     * E:0-5 拉大垮
     */

    enum S {
        FULL,
        SSS,
        SS,
        S,
        A,
        B,
        C,
        D,
        E,

        UNSETTLED,//未解决
        NONE,//未通过
        OT,//over time 超时
        OOM,// out of memory 内存溢出
    }

    enum WAY{
        SELF,
        COPIED,
        REFER//参考
    }
}
