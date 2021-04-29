package com.example.leetcode.utils;

/**
 * @Auther: Rxh
 * @Date: 2019/9/4 11:31
 * @Description:
 */
public class CharUtils {

    public static int getIntByChar(char ch) {
//        switch (ch) {
//            case 48: return 0;
//            case 49: return 1;
//            case 50: return 2;
//            case 51: return 3;
//            case 52: return 4;
//            case 53: return 5;
//            case 54: return 6;
//            case 55: return 7;
//            case 56: return 8;
//            case 57: return 9;
//            default: return -1;
//        }
        if (ch >= 48 && ch <= 57) {
            return ch - 48;
        }
        return -1;
    }

    /**
     * a-z : 97-122
     * A-Z : 65-90
     *
     * @param ch
     * @return
     */
    public static char getUpper(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 32);
        }
        return ch;
    }

    public static char getLower(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char) (ch + 32);
        }
        return ch;
    }

    public static void main(String[] args) {
        char ch1 = 'a';
        char ch2 = 'z';
        char ch3 = 'A';
        char ch4 = 'Z';
        System.out.println((int) ch1);
        System.out.println((int) ch2);
        System.out.println((int) ch3);
        System.out.println((int) ch4);
    }
}
