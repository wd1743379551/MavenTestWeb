package com.yxj.calc;

/**
 * Z字型数字
 */
public class LeecodeDemo6 {


    private static String convert(String s, int numRows) {
        if (s.length() < 2) {
            return s;
        }
        if (numRows == 1) {
            return s;
        }
        char[][] charArr = new char[numRows][];
        System.out.println("二维数组长度" + charArr.length);
        int colIndex = 0;
        int rowIndex = 0;
        int strLength = s.length();
        int cols = strLength / 2 + 1;
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = new char[cols];
        }
        for (int i = 0; i < strLength; i ++) {
            if (i < numRows) {
                charArr[rowIndex][colIndex] = s.charAt(i);
                if (i != numRows - 1) {
                    rowIndex++;
                } else {
                    rowIndex--;
                }
            } else {
                if (colIndex == 0) {
                    colIndex++;
                }
                if ((colIndex % (numRows - 1)) == 0) {
                    charArr[rowIndex][colIndex] = s.charAt(i);
                    if (rowIndex == numRows - 1) {
                        colIndex++;
                        rowIndex--;
                    } else {
                        rowIndex++;
                    }
                } else {
                    charArr[rowIndex][colIndex] = s.charAt(i);
                    rowIndex --;
                    colIndex++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] chars : charArr) {
            for (char c : chars) {
                if (c != '\u0000') {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String paypalishiring = convert("ABC", 2);
        System.out.println(paypalishiring);
    }

}
