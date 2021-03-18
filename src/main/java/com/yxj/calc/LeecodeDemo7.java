package com.yxj.calc;

/**
 * 数字反转
 */
public class LeecodeDemo7 {

    public static int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }
        int temp = x;
        int result = 0;
        while (temp != 0) {
            int y = temp % 10;
            if (temp > Integer.MAX_VALUE/10 || (temp == Integer.MAX_VALUE/10 && y > 7) ) {
                return 0;
            }
            if (temp < Integer.MIN_VALUE/10 || (temp == Integer.MIN_VALUE/10 && y < -8)) {
                return 0;
            }
            result = result * 10 + y ;
            temp /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(120));
    }
}
