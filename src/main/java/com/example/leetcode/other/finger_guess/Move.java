//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.leetcode.other.finger_guess;

public enum Move {
    SCISSORS("剪刀", 1),
    ROCK("石头", 2),
    PAPER("布", 3),
    NOOP("弃权", -99);

    private String desc;
    private int num;

    Move(String desc, int num) {
        this.desc = desc;
        this.num = num;
    }

    public String getDesc() {
        return this.desc;
    }
    public int getNum() {
        return this.num;
    }

    public int battle(Move anotherMove){
        int baitoures = this.num - anotherMove.num;
        if (baitoures == 1 || baitoures == -2){
            return 1;//win
        }else if (baitoures == 0){
            return 0;//tie
        }else {
            return -1;//fail
        }
    }
}
