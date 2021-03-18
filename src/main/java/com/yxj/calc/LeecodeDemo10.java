package com.yxj.calc;

/**
 * 回文数判断
 */
public class LeecodeDemo10 {

    public static boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int y = x;
        int temp = 1;
        while (y >= 10) {
            temp *=10;
            y /= 10;
        }
        int z = 10;
        while (x > 0) {
            int first = x / temp;
            int end = x % z;
            if (first != end) {
                return false;
            }
            x = (x % temp) / 10;
            temp/=100;
        }
        return true;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {

        System.out.println(isPalindrome2(1000021));
    }

}
