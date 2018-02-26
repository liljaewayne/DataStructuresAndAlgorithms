package com.dynamic.programming;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(4));
    }

    private static int fibonacci(int n) {
        //          1  1  2  3  5  8  13
        //             ^  ^  ^
        //             a  b  t

        // 值的前前位置
        int a = 1;
        // 值的前位置
        int b = 1;
        for (int i = 0; i < n - 2; i++) {
            int tmp = a + b;
            a = b; // a move to b
            b = tmp; // b move to tmp
        }
        // b移动后已经是值的位置
        return b;
    }
}
