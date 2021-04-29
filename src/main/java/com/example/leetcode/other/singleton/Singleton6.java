package com.example.leetcode.other.singleton;

/**
 * @Auther: Rxh
 * @Date: 2020/1/2 16:49
 * @Description: 单例模式6
 * 枚举类也可以实现单例,但这种与普通的饿汉式区别不大,只是换了一种优雅的实现方式
 * 枚举类的构造函数默认是私有的,而且它是线程安全的,所以枚举类天生符合单例的条件
 */
public enum Singleton6 implements Cloneable{
    SINGLETON
    ;

    Singleton6(){
        System.out.println("Singleton6 construct....");
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

}
