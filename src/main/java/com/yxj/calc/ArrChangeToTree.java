package com.yxj.calc;


public class ArrChangeToTree {


    public static TreeNode arrToTree(int[] arr) {
        return arrToTree(arr, 0, arr.length - 1);
    }


    public static TreeNode arrToTree(int[] arr, int start, int end) {
        if (arr == null) {
            throw new NullPointerException();
        }
        if (arr.length - 1 < end) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = arrToTree(arr, 0, mid - 1);
        root.rignt = arrToTree(arr, mid + 1, end);
        return root;
    }


    public static class TreeNode{
        TreeNode left;
        TreeNode rignt;
        int value;

        public TreeNode(TreeNode left, TreeNode rignt, int value) {
            this.left = left;
            this.rignt = rignt;
            this.value = value;
        }

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRignt() {
            return rignt;
        }

        public void setRignt(TreeNode rignt) {
            this.rignt = rignt;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "left=" + left +
                    ", rignt=" + rignt +
                    ", value=" + value +
                    '}';
        }
    }

}


