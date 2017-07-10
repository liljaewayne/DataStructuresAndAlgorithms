package com.leetcode.a216;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * <p>
 * Output:
 * <p>
 * [[1,2,4]]
 * <p>
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * <p>
 * Output:
 * <p>
 * [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * 没做出来
 */
public class Solution {

    private HashSet<Integer> elements;

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (n < 1 || n > 45) {
            return results;
        }
        if (k < 1 || k > 9) {
            return results;
        }
        elements = new HashSet<>();

        for (int i = 1; i <= 9; i++) {
            elements.add(i);
        }

        return results;
    }

    /*

     */
    private void helper(int target, List<Integer> result) {
        if (elements.contains(target)) {
            // 加到结果中
            result.add(target);
            elements.remove(target);
        }
    }
}
