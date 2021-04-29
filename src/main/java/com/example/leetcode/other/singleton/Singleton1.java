package com.example.leetcode.other.singleton;

/**
 * @Auther: Rxh
 * @Date: 2020/1/2 16:49
 * @Description: 单例模式1,饿汉式
 * 以空间换时间,不管用不用先初始化好实例放在内存中
 */
public class Singleton1 {
    private static final Singleton1 INSTANCE = new Singleton1();
    public static Singleton1 getInstance(){
        return INSTANCE;
    }
    private Singleton1(){}
}
