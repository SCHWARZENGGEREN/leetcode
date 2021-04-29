package com.example.leetcode.other.singleton;

/**
 * @Auther: Rxh
 * @Date: 2020/1/2 16:49
 * @Description: 单例模式2,懒汉式
 * 对饿汉式的优化,以时间换取空间,在需要时才实例化
 */
public class Singleton2 {
    private static Singleton2 INSTANCE = null;
    public static Singleton2 getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
    private Singleton2(){}
}
