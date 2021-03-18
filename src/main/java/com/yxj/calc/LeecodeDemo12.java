package com.yxj.calc;

/**
 * 最大面积水柱
 */
public class LeecodeDemo12 {

    public static int maxArea(int[] height) {
        int length = height.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return height[0];
        }
        int maxArea = 0;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int heightTemp = height[left] > height[right] ? height[right] : height[left];
            int currentArea = (right - left) * heightTemp;
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {

    }

}
