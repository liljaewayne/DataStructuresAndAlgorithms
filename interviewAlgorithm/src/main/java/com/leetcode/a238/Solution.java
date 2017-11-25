package com.leetcode.a238;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Solve it without division and in O(n).
 * <p>
 * For example, given [1,2,3,4], return [24,12,8,6].
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int zeroIndex = -1;
        int k = 1;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) {
                if (zeroIndex == -1) {
                    zeroIndex = i;
                } else {
                    zeroIndex = nums.length;
                }

            } else {
                k *= nums[i];
            }

        }

        if (zeroIndex == -1) {// 输入数组没有0元素

            for (int i = 0; i < nums.length; i++) {
                result[i] = k / nums[i];
            }

        } else if (zeroIndex != -1 && zeroIndex < nums.length) {// 输入数组有1个0元素
            result[zeroIndex] = k;
        } /*else {}*/ // 输入数组有2个或以上0, 结果集全是0即可

        return result;
    }

    public int[] productExceptSelf_version1(int[] nums) {
        int[] result = new int[nums.length];

        List<Integer> zeroIndexes = new ArrayList<>();

        int k = 1;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) {
                zeroIndexes.add(i);
            } else {
                k *= nums[i];
            }

        }

        if (zeroIndexes.size() == 0) {// 输入数组没有0元素

            for (int i = 0; i < nums.length; i++) {
                result[i] = k / nums[i];
            }

        } else if (zeroIndexes.size() == 1) {// 输入数组有1个0元素
            result[zeroIndexes.get(0)] = k;
        } /*else {}*/ // 输入数组有2个或以上0, 结果集全是0即可


        return result;
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf_violence(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {

            result[i] = 1;

            for (int j = 0; j < nums.length; j++) {
                if (j == i) {
                    continue;
                } else {
                    result[i] *= nums[j];
                }
            }

        }

        return result;
    }
}
