package com.leetcode.a167;

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * <p>
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        // [left .. right]
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left + 1, right + 1};
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {// numbers[left] + numbers[right] > target
                right--;
            }
        }

        return new int[]{-1, -1};
    }
}