package com.example.leetcode.other.hanno_tower;

import com.example.leetcode.utils.StringUtil;
import com.example.leetcode.utils.Util;

import java.util.Stack;

/**
 * @Auther: Rxh
 * @Date: 2019/12/2 16:21
 * @Description:
 */
public class HannoDemo {

    /**
     * 使用分治法解决汉诺塔问题
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        Stack<Integer> root = new Stack<>(), temp = new Stack<>(), dest = new Stack<>();
//        for (int i = 5; i >= 1; i--) {
//            root.push(i);
//        }
//        placeThree(root, temp, dest);

        new HannoTower(3).move();
    }


    /**
     * 将123 从root转移到dest,步骤:
     * s->root;t->temp;d->dest
     * s   t   d
     * 123
     * 23      1
     * 3   2   1
     * 3  12
     * 12   3
     * 1   2   3
     * 1      23
     * 123
     *
     * @param root
     * @param temp
     * @param dest
     */
    private static void placeThree(Stack<Integer> root, Stack<Integer> temp, Stack<Integer> dest) throws Exception {
        print(root, temp, dest);
        move(root, dest);//root(123) move 1 to dest
        print(root, temp, dest);
        move(root, temp);//root(23) move 2 to temp
        print(root, temp, dest);
        move(dest, temp);//dest(1) move 1 to temp(2)
        print(root, temp, dest);
        move(root, dest);//root(3) move 3 to dest
        print(root, temp, dest);
        move(temp, root);//temp(12) move 1 to root
        print(root, temp, dest);
        move(temp, dest);//temp(2) move 2 to dest(3)
        print(root, temp, dest);
        move(root, dest);//root(1) move 1 to dest(23)
        print(root, temp, dest);
    }

    private static void move(Stack<Integer> root, Stack<Integer> dest) throws Exception {
        if (!root.isEmpty()) {
            Integer speek = root.peek();
            if (dest.isEmpty() || speek < dest.peek()) {
                dest.push(root.pop());
            } else {
                throw new Exception("[Hanno Tower] can't place (" + speek + ") above (" + dest.peek() + ") !");
            }
        }
    }

    private static void print(Stack<Integer> root, Stack<Integer> temp, Stack<Integer> dest) {
        int total = (root.size() + temp.size() + dest.size()) * 3;
        String s1 = Util.getStackToStringReverse(root);
        String s2 = Util.getStackToStringReverse(temp);
        String s3 = Util.getStackToStringReverse(dest);
        System.out.println(
                "root: " + s1 + StringUtil.getDuplicateStr(total - s1.length() + 2, " ") +
                        "temp: " + s2 + StringUtil.getDuplicateStr(total - s2.length() + 2, " ") +
                        "dest: " + s3
        );
    }

    static class HannoTower {
        private int level;
        private Tower<Integer> root = new Tower<>("塔1");
        private Tower<Integer> temp = new Tower<>("塔2");
        private Tower<Integer> dest = new Tower<>("塔3");

        public HannoTower(int level) {
            this.level = level;
            for (int i = level; i >= 1; i--) {
                root.push(i);
            }
        }

        public void move() {
            int num = level;
            printTower();
            while (num-- > 0) {
                move(num, root, temp, dest);
            }
        }


        public void move(Tower<Integer> root, Tower<Integer> temp, Tower<Integer> dest){
            while (!root.isEmpty()){

            }
        }

        /**
         * 将root的上面levelNum层挪到dest
         * @param root
         * @param temp
         * @param dest
         * @param levelNum
         */
        public void move(Tower<Integer> root, Tower<Integer> temp, Tower<Integer> dest,int levelNum){

        }


        /**
         * @param targetNum root上面需要移动的目标数字
         * @param root
         * @param temp
         * @param dest
         */
        private void move(int targetNum, Tower<Integer> root, Tower<Integer> temp, Tower<Integer> dest) {
            if (!root.isEmpty()) {
                if (root.peek() < targetNum) {
                    move(targetNum - 1, root, dest, temp);//先将root上的n-1挪往dest
                    if (!temp.isEmpty()) {//再将temp上的n-2挪到dest
                        move(targetNum - 1, temp, dest, root);
                    }
                } else if (root.peek() == targetNum) {
                    if (canPlace(targetNum, dest)) {
                        move1(root, dest);
                    } else {
                        Util.printThenExit("流程错误!! move " + root.name + " to " + dest.name);
                        printTower();
                    }
                }
            }
        }


        /**
         * 从root向dest移动最上面一层
         *
         * @param root
         * @param dest
         */
        private void move1(Tower<Integer> root, Tower<Integer> dest) {
            if (!root.isEmpty()) {
                Integer peek = root.peek();
                if (dest.isEmpty() || dest.peek() > peek) {
                    System.out.println("    move" + root.name + "--" + peek + "-->" + dest.name);
                    dest.push(root.pop());
                    printTower();
                } else {
                    Util.printThenExit("cann't place [" + peek + "] on [" + dest.peek() + "] !");
                }
            }
        }

        private boolean canPlace(int num, Tower<Integer> dest) {
            return dest.isEmpty() || dest.peek() > num;
        }

        private void printTower() {
            int total = level * 3;
            String s1 = Util.getStackToStringReverse(root);
            String s2 = Util.getStackToStringReverse(temp);
            String s3 = Util.getStackToStringReverse(dest);
            System.out.print(
                    root.name + " : " + s1 + StringUtil.getDuplicateStr(total - s1.length() + 2, " ") +
                            temp.name + " : " + s2 + StringUtil.getDuplicateStr(total - s2.length() + 2, " ") +
                            dest.name + " : " + s3
            );
        }
    }

    static class Tower<T> extends Stack<T> {
        String name;

        public Tower(String name) {
            super();
            this.name = name;
        }
    }
}
