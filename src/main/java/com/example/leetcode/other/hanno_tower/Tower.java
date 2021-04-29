package com.example.leetcode.other.hanno_tower;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Rxh
 * @Date: 2019/12/2 15:44
 * @Description: 汉诺塔模型
 */
public class Tower {

    private int high;
    private List<Integer> tower = new ArrayList<>();

    public Tower(int high) {
        this.high = high;
    }

    public boolean put(int num) {
        if (!tower.isEmpty()) {
            if (tower.get(tower.size() - 1) >= num) {
                return tower.add(num);
            }
            return false;
        } else {
            return tower.add(num);
        }
    }

    public void remove() {
        if (!tower.isEmpty()) {
            tower.remove(tower.size() - 1);
        }
    }

    public boolean full() {
        return tower.size() == high;
    }
}
