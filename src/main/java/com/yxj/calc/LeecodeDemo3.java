package com.yxj.calc;

/**
 * 无重复字符的最长子串长度
 */
public class LeecodeDemo3 {


    private static int lengthLongestSubString(String str) {
        if (str == null || str.length() == 0 ) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < length; i++) {
            sb.append(str.charAt(i));
            if ((length - i) <= maxLength) {
                break;
            }
            for (int j = i + 1; j < length; j++) {
                char c = str.charAt(j);
                if (sb.indexOf(String.valueOf(c)) != -1) {
                    break;
                }
                sb.append(c);
                if (sb.length() > maxLength) {
                    maxLength = sb.length();
                }
            }
            sb.delete(0, sb.length());
        }
        return maxLength;
    }


    public static void main(String[] args) {
        System.out.println(lengthLongestSubString("au"));
    }

}
