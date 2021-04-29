package com.example.leetcode.other.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: Rxh
 * @Date: 2020/1/3 10:50
 * @Description: 总结:一般情况下可以根据需要选择使用静态内部类或者双重校验锁或者饿汉式
 * 懒汉式和饿汉式和静态内部类构造的单例都会被反射破坏,枚举类则不受影响
 * 所以如果要保证万无一失,虽好还是使用枚举
 *
 * 单例模式破解方法和防御方法:
 * 1,反射会破坏枚举类以外的单例实现方式;应对这种攻击只能使用枚举类,在反射机制中,完全避开了枚举类型
 * 2,序列化会破;对应方法,在实体类中添加readObject方法,提供相关获取对象方式,没有的话就会创建新对象
 * 3,对象克隆,会绕过构造器直接从内从中复制对象;
 *
 *
 * 一夜天山月苍苍,怒浪滔滔满身狂
 * 一眼是非百年功,玄黄荡荡血染红
 * 泽国江山谁与共,骄魂傲骨葬英雄
 * 乱世烽火路不终,轰掣天下胜蛟龙
 */
public class SingletonDemo {

    public static void main(String[] args) {
//        showReflectDestroySingleton();
//        showReflectDestroySingleton1();
//        showSerializationDestorySingleton();
        showCloneableDestorySingleton();
    }

    /**
     * 反射可以破坏一般的单例模式,但无法破坏枚举
     */
    private static void showReflectDestroySingleton() {
        try {
            Class<?> aClass = Singleton4.class;
            Constructor<?> constructor = aClass.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            System.out.println(constructor.newInstance());

            Method getInstance = aClass.getDeclaredMethod("getInstance");
            Object instance1 = getInstance.invoke(null);
            System.out.println(instance1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showReflectDestroySingleton1() {
        try {
            Class<?> aClass = Singleton6.class;
            Field field = aClass.getField("SINGLETON");
            System.out.println(field.get(null));
            System.out.println(aClass.newInstance());

            Constructor<?> constructor = aClass.getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();
            System.out.println(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 除了反射外,序列化也会破坏单例
     * 应对办法,在对象中添加readResolve方法,指定获取对象方式
     */
    private static void showSerializationDestorySingleton() {
        File file = new File("C:\\Users\\Admin\\Desktop\\demo.txt");
        try (
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        ) {
            Singleton6 instance1 = Singleton6.SINGLETON;
            out.writeObject(instance1);

            System.out.println(instance1);
            System.out.println(in.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当一个类实现了Cloneable接口时,可以调用基类中的clone()方法创建实例,
     * 但此方法并未经过构造函数,而是直接从内从中copy一个实例副本
     *
     * 因此,单例要避免实现Cloneable和复写clone方法
     */
    private static void showCloneableDestorySingleton(){
        try {
            Singleton4 instance = Singleton4.getInstance();
            System.out.println(instance);
            System.out.println(instance.clone());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * dateformat的bug:
     * 对于YYYY-MM-dd格式,如果本周跨年,则认为年份就是下一年
     */
    private static void showDateFormatBug(){
        Calendar instance = Calendar.getInstance();
        instance.set(2019,Calendar.DECEMBER,31);
        Date date = instance.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
        System.out.println(new SimpleDateFormat("YYYY-MM-dd").format(date));
    }
}
