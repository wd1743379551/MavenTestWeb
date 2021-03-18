package com.yxj.calc;

/**
 * 字符串转换成数字
 */
public class LeecodeDemo9 {

    public static int myAtoi(String s) {

        char[] chars = s.toCharArray();
        int result = 0;
        boolean fuFlag = false;
        boolean frontFlag = false;
        boolean hasFrontFlag = false;
        for (int i = 0; i < chars.length; i++) {
            int charVal = chars[i];

            if (i == 0 && charVal == 45) {
                fuFlag = true;
                continue;
            } else if (i == 0 && charVal == 43) {
                continue;
            }
            if ((i == 0 && charVal == 32) || (frontFlag && charVal == 32)) {
                frontFlag = true;
                hasFrontFlag = true;
                continue;
            } else {
                if (hasFrontFlag) {
                    frontFlag = false;
                    hasFrontFlag = false;
                    if (charVal == 45) {
                        fuFlag = true;
                        continue;
                    } else if (charVal == 43) {
                        continue;
                    }
                }
            }
            int diff = charVal - 48;
            if (charVal >= 48 & charVal <= 57) {
                if (fuFlag) {
                    if (-result < Integer.MIN_VALUE/10  || (-result == Integer.MIN_VALUE / 10 && diff > 8)) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE/10 && diff > 7)) {
                        return Integer.MAX_VALUE;
                    }
                }
                result = result * 10 + diff;
            } else {
                if (fuFlag) {
                    return -result;
                }
                return result;
            }
        }
        if (fuFlag) {
            return -result;
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(myAtoi("    -12"));
    }
}
