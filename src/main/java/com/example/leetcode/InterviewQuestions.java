package com.example.leetcode;

/**
 * @Auther: Rxh
 * @Date: 2021/4/19 10:34
 * @Description:
 */
public class InterviewQuestions {


    public static void main(String[] args) {
        StringPool.demo4();
    }

    /**
     * 字符串常量池:
     * 创建字符串对象时,一般有几种情况:
     * 1,直接赋值,直接赋值时会先去常量池寻找,如果常量池有该字符常量,则直接让变量指向常量池引用;如果没有就将字符串放入常量池并让变量指向
     * 2,使用new创建,会在堆内存分配空间,不会用到常量池,如果要用,就要调intern()方法去常量池
     * "注册":如果常量池有该字符串常量,则让对象指向常量池引用;如果没有,在jdk1.7以前,会直接将对象中的字符串拷贝到常量池中并返回引用;
     * 如果jdk是1.7及以后版本,会直接让常量池常量指向堆内存中对象引用,相当于"挂名"
     * 3,使用"+"拼接字符时:如果拼接的是final修饰的字符变量(final字符在编译阶段都会被替换为常量)或者常量,编译器会直接拼接字符串,并在常量池"注册"
     * 如果是变量,底层会通过StringBuilder拼接,所以引用会变;
     * 延伸1:如果拼接字符串中出现null时,根据StringBuilder.append()方法源码可知,并不会出现NPE,而是将"null"拼进去
     * 延伸2:如果代码中用到很多拼接字符串时,有必要考虑使用StringBuilder处理,这样避免了在底层new过多StringBuilder临时对象
     * 其他:如果在声明对象之前,其字符常量已经存在于常量池,那么会直接从常量池拿引用
     * <p>
     * intern方法:功能相当于将字符串"注册"到常量池
     *
     * [2021-4-22 13:51:20] 在编译期,如果字符直接出现或者是fianl字符也好,拼接字符也好,都会放入常量池
     *                      除此之外,编译器"未知"的,一般都会生成对象
     */
    static class StringPool {

        static void demo1() {
            String s1 = "1";
            String s2 = s1 + "";

            System.out.println(s1 == s2);//false 运算之后创建了新对象
            s2.intern();
            System.out.println(s1 == s2);//false
        }

        static void demo2() {
            String s2 = new String("1");
            s2.intern();
            String s1 = "1";
            System.out.println(s1 == s2);//false s2创建之前(编译阶段)已经访问了常量池

            String s3 = s1 + "1";
            s3.intern();
            String s4 = "11";
            System.out.println(s3 == s4);//true 这种创建方式,编译器无法得知s3的内容,也就不会访问常量池
            System.out.println(s3 == s3.intern());//true 套娃


            String str1 = new String("1") + new String("2");
            System.out.println(str1.intern() == str1);//true (jdk7后)调用intern注册其实指向了本身
            System.out.println(str1 == "12");//true 经过上面的intern后常量池指向了本身
        }

        /**
         * 编译后变量s3直接指向了拼接后的常量
         */
        static void demo3() {
            String s1 = "hello";
            String s2 = "he";
            String s3 = s2 + "llo";

            final String _s1 = "1";
            final String _s2 = "2";

            String s4 = _s1 + _s2;
            String s5 = new String("hello");

            System.out.println(s1 == s3);//false s3并非直接赋值,因此在堆内存创建了对象
            System.out.println(s3 == s4);//false s4同上
            System.out.println(s1 == s4);//false
            System.out.println(s1 == s5.intern());//true
        }

        /**
         * StringBuilder在toString过程中访问了常量池,并把字符串注册到了常量池
         */
        static void demo4() {
            String s1 = new String("Hel") + "lo";
            s1.intern();
            String s2 = new StringBuffer("He").append("ll").append("o").toString();
//            String s3 = s2.intern();
            String s4 = new String("Hel") + "l";
            System.out.println("s1 == s2? " + (s1 == s2));//false
//            System.out.println("s1 == s3? " + (s1 == s3));//true
            System.out.println("s1 == s2.intern()? " + (s2.intern() == s1));//true
            System.out.println(s4.intern() == s4);//true 说明StringBuilder拼接过程中没有访问常量池,只有最后toString时访问了


            String s5 = new String("Hel") + "lo";
            s5.intern();
            String s6 = new StringBuffer("He").append("ll").append("o").toString();

            System.out.println(s5 == s6);//false在字符已注册的情况下就不适用了
        }
    }
}
