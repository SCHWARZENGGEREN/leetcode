package com.example.leetcode.imitate;

/**
 * @Auther: Rxh
 * @Date: 2019/8/30 15:52
 * @Description:
 */
public class Result<T> {
    T res;

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

    public Result() {
    }
    public Result(T res) {
        this.res = res;
    }
}
