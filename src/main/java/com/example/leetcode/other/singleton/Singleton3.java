package com.example.leetcode.other.singleton;

/**
 * @Auther: Rxh
 * @Date: 2020/1/2 16:49
 * @Description: 单例模式3,懒汉式双重校验锁(Double Check Lock,DCL)
 * 对饿汉式的进一步优化,以时间换取空间,在需要时才实例化
 * 由于多线程下在getInstance判断后可能出现线程切换导致生成多个实例,
 * 因此在原有基础上加上synchronized修饰相关代码,但这个关键字效率很低(时间开销是正常执行的100倍),
 * 为了提升效率在外层进一步判断,这样只在第一次获取对象时比较慢
 */
public class Singleton3 {
    private static Singleton3 INSTANCE = null;
    public static Singleton3 getInstance(){
        if (INSTANCE == null){
            synchronized (Singleton3.class){
                if (INSTANCE == null){
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
    private Singleton3(){}
}
