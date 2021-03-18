package com.yxj.calc;

import java.util.ArrayList;
import java.util.List;

public class LeecodeDemo8 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (i == 0) {
                    list.add(matrix[i][j]);
                } else if (i != n - 1) {
                    int x = n - 1 - j;
                    list.add(matrix[i][x]);
                } else {

                }
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }


}
