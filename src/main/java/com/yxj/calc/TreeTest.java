package com.yxj.calc;

public class TreeTest {


    public static void main(String[] args) {
        int[] arr = {2,3,5,6,8,9};
        ArrChangeToTree.TreeNode treeNode = ArrChangeToTree.arrToTree(arr);

        System.out.println(treeNode);

    }
}
