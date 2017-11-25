package com.leetcode.a11;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[]{2, 3, 10, 5, 7, 8, 9}));
    }

    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int result = 0;

        while (left < right) {
            // 正方形面积
            int area = Math.min(height[left], height[right]) * (right - left);
            result = Math.max(result, area);

            // 要比较的两条边不能过界
            if ((right - 1) >= (left + 1)) {
                if (height[left + 1] < height[right - 1]) {
                    right--;
                } else {
                    left++;
                }
            } else {
                break;
            }

        }
        return result;
    }
}