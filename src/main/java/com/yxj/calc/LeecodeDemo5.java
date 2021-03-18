package com.yxj.calc;

/**
 * 最长回文子串
 */
public class LeecodeDemo5 {

    /**
     * 中心扩散法
     * 1.有两种中心 单字符中心和双字符中心
     */
    private static String longestHuiwenStr2(String s) {
        if (s.length() < 2) {
            return s;
        }
        int length = s.length();
        String maxStr = "";
        for (int i = 0; i < length; i++) {
            // 获取单字符为中心的最长
            String oddStr = getMaxHuiwenStr(s, i, i);
            String evenStr = getMaxHuiwenStr(s, i, i+1);
            String tempStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (tempStr.length() > maxStr.length()) {
                maxStr = tempStr;
            }
        }
        return maxStr;
    }


    /**
     * 获取从指定中心扩散的最大回文字符串
     * @param s 源字符串
     * @param start 扩散中心开始索引
     * @param end 扩散中心结束索引
     * @return
     */
    private static String getMaxHuiwenStr(String s, int start, int end) {
        int length = s.length();
        while (start >= 0 && end < length) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
            start--;
            end++;
        }
        return s.substring(start+1, end);
    }

    public static void main(String[] args) {
        String babab = longestHuiwenStr2("babf23");
        System.out.println(babab);
    }

    /**
     * 暴力破解法
     * @param s
     * @return
     */
    private static String longestHuiwenStr(String s) {
        if (isHuiWenStr(s)) {
            return s;
        }
        int maxLength = 0;
        String maxStr = "";
        int length = s.length();
        for (int j = 0; j < length; j ++ ) {
            for (int i = length; i > j; i--) {
                String substring = s.substring(j, i);
                if (isHuiWenStr(substring) && maxLength < substring.length()) {
                    maxLength = substring.length();
                    maxStr = substring;
                }
            }
        }
        return maxStr;
    }

    private static boolean isHuiWenStr(String s) {
        int left = 0;
        int right = s.length() - 1;
        boolean result = true;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                result = false;
                break;
            }
            left++;
            right--;
        }
        return result;
    }
}
