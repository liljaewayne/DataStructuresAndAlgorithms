package com.leetcode.a209;

/**
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
    }

    public int minSubArrayLen(int s, int[] nums) {
        int result = nums.length + 1;

        // 窗口 [back .. front] 和大于等于s
        int back = 0;
        int front = -1;

        int nowSum = 0;

        while (front < nums.length) {
            if (nowSum >= s) {
                result = Math.min(result, front - back + 1);
                nowSum -= nums[back];
                back++;
            } else {
                front++;
                if (front < nums.length) {
                    nowSum += nums[front];
                }
            }
        }

        if (result == nums.length + 1) {
            result = 0;
        }

        return result;
    }
}
