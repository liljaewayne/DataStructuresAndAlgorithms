package com.dynamic.programming;

import java.util.HashMap;

/**
 * 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
 * <p>
 * 比如，每次走1级台阶，一共走10步，这是其中一种走法。我们可以简写成 1,1,1,1,1,1,1,1,1,1。
 * <p>
 * 再比如，每次走2级台阶，一共走5步，这是另一种走法。我们可以简写成 2,2,2,2,2。
 * <p>
 * 1,2,3,5,8,13,21,34,55,89
 */
public class Example1 {
    public static void main(String[] args) {
        System.out.println(new Example1().climbingWays(10));
        System.out.println(new Example1().climbingWays2(10));
        System.out.println(new Example1().climbingWays3(10));
    }

    /**
     * 第三步, 动态规划, 自底向上计算, 优化空间复杂度
     * T=O(n) M=O(1)
     *
     * @param n
     * @return
     */
    public int climbingWays3(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        /*
        只需要缓存前两个数, 一步步向后求解, 直到求解到最后一个
         */
        int a = 1;
        int b = 2;
        int result = -1;

        for (int i = 3; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }

        return result;
    }

    /**
     * 第二步, 加缓存
     * T=O(n) M=O(n)
     */
    public int climbingWays2(int n) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return helper2(n, cache);
    }

    private int helper2(int n, HashMap<Integer, Integer> cache) {
        assert n > 0;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            int result = helper2(n - 1, cache) + helper2(n - 2, cache);
            cache.put(n, result);
            return result;
        }
    }

    /**
     * 第一步思考过程, 找出3个可递归条件
     * 最优子结构: f(10) = f(9) + f(8): f(9)和f(8) 是f(10) 的最优子结构
     * 边界: f(1) = 1 ; f(2) = 2; 没有边界将会死递归
     * 状态转移方程: f(n) = f(n-1) + f(n-2): 这是动态规划的核心, 决定了问题的每一个阶段和下个阶段的关系
     * <p>
     * T=O(n^2) M=O(1)
     *
     * @param n
     * @return
     */
    public int climbingWays(int n) {
        assert n > 0;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return climbingWays(n - 1) + climbingWays(n - 2);
    }
}
