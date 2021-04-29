package com.example.leetcode.other;

import javax.swing.*;
import java.awt.*;

public class Test {
    static Thread threadObj1;
    static Thread threadObj2;
    JFrame frame;
    JPanel panel;
    JLabel label1, label2;
    static JLabel label3;
    static JProgressBar progressBar1;
    static JProgressBar progressBar2;

    public static void main(String[] args) {
        Test test = new Test();
        test.go();
        threadObj1 = new ThreadClass1();
        threadObj2 = new ThreadClass2();
        threadObj1.setPriority(6);
        threadObj2.setPriority(4);
        threadObj1.start();
        threadObj2.start();
    }

    void go() {
        frame = new JFrame("赛马");
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        label1 = new JLabel("一号马");
        label2 = new JLabel("二号马");
        label3 = new JLabel("加油！");
        progressBar1 = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        progressBar1.setStringPainted(true);
        progressBar2 = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        progressBar2.setStringPainted(true);
        panel.add(label1);
        panel.add(progressBar1);
        panel.add(label2);
        panel.add(progressBar2);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(label3, BorderLayout.SOUTH);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ThreadClass1 extends Thread {
    public void run() {
        while (Test.progressBar1.getValue() < 100) {
            Test.progressBar1.setValue(Test.progressBar1.getValue() + 1);
            System.out.println(Test.progressBar1.getValue());
            try {
                Thread.sleep((int) (Math.random() * 300 + 100));
            } catch (InterruptedException e) {
            }
        }
        if (Test.progressBar1.getValue() == 100 && Test.progressBar2.getValue() != 100)
            Test.label3.setText("胜利者：1号马！");
    }
}

class ThreadClass2 extends Thread {
    public void run() {
        while (Test.progressBar2.getValue() < 100) {
            Test.progressBar2.setValue(Test.progressBar2.getValue() + 1);
            System.out.println(Test.progressBar2.getValue());
            try {
                Thread.sleep((int) (Math.random() * 300 + 100));
            } catch (InterruptedException e) {
            }
        }
        if (Test.progressBar2.getValue() == 100 && Test.progressBar1.getValue() != 100)
            Test.label3.setText("胜利者：2号马！");
    }
}