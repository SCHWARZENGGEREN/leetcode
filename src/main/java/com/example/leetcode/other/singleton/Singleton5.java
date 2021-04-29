package com.example.leetcode.other.singleton;

/**
 * @Auther: Rxh
 * @Date: 2020/1/2 16:49
 * @Description: 单例模式5
 * 上述过程已将懒汉式优化到极致,但略显麻烦,除了懒汉式,饿汉式,还可以使用内部类实现单例
 * 内部类的优点是: 线程安全,且外部类加载时并不需要立即加载内部类,即不去初始化INSTANCE,
 * 当getInstance方法未被调用时,相关代码只是一个符号,只有被调用时,内部类才会真正加载,符号被替换为引用,而且这个过程是线程安全的
 * 因此这种方式既保证了线程安全,实现了对象延迟创建
 * 但这种方式也存在限制,即只使用于无参构造的对象
 * 枚举类无法被反射破坏
 *
 */
public class Singleton5 {
    public static Singleton5 getInstance() {
        return Inner.INSTANCE;
    }

    private Singleton5() {
    }

    private static class Inner {
        private static final Singleton5 INSTANCE = new Singleton5();
    }
}
