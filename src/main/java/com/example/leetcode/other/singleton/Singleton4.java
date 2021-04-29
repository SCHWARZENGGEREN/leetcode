package com.example.leetcode.other.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @Auther: Rxh
 * @Date: 2020/1/2 16:49
 * @Description: 单例模式4
 * DCL的懒汉式看似完美,但由于Jvm的乱序执行可能获取的对象构造不完全,所以DCL还不是完美的单例解决方案
 * 对此,可以进一步优化,对INSTANCE变量加上修饰符volatile,这样就能保证对象构造正常,
 * volatile确保INSTANCE每次均在主内存中读取，这样虽然会牺牲一点效率，但也无伤大雅
 * jdk1.5以后,volatile关键字的功能被扩展,被它修饰的变量在构造时能避免乱序执行
 */
public class Singleton4 implements Serializable, Cloneable {
    private static volatile Singleton4 INSTANCE = null;

    public static Singleton4 getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (Singleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }

    private Singleton4() throws Exception {
        System.out.println("Singleton4: 构造函数执行...");
    }

    /**
     * 防止反序列化破坏单例，反序列化时，如果定义了readResolve方法，则直接返回方法指定的对象，而不需要单独再创建对象
     * https://blog.csdn.net/weixin_42130471/article/details/89602999
     */
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
