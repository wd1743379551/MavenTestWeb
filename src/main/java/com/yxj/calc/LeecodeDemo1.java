package com.yxj.calc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class LeecodeDemo1 {


    private static int[] twoSum(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] result = new int[2];
        for (int i = 0; i< arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if ((arr[i] + arr[j]) == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    private static int[] twoSum2(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{i, map.get(target - arr[i])};
            } else {
                map.put(arr[i], i);
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] testArr = new int[]{1,6,9,3,8};
        int[] ints = twoSum2(testArr, 12);
        System.out.println(Arrays.toString(ints));
    }

}
