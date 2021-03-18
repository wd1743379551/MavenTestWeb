package com.yxj.calc;

/**
 * 链表数字相加
 */
public class LeecodeDemo2 {


    private static ListNode addListNode(ListNode a, ListNode b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }
        ListNode newNode = new ListNode(0);
        ListNode first = newNode;
        int x = 0;
        int length = 0;
        while (a != null || b != null) {
            int aValue = 0;
            int bValue = 0;
            if (a != null) {
                aValue = a.val;
                a = a.next;
            }
            if (b != null) {
                bValue = b.val;
                b = b.next;
            }
            int i = aValue + bValue;
            if (x > 0) {
                i = i + x;
            }
            x = i / 10;
            i = i % 10;

            if (length == 0) {
                newNode.val = i;
            } else {
                newNode.next = new ListNode(i);
                newNode = newNode.next;
            }
            length++;
        }
        if (x != 0) {
            newNode.next = new ListNode(x);
        }
        return first;
    }

    private static void printListNode(ListNode first) {
        if (first != null) {
            ListNode current = first;
            while (current != null) {
                System.out.print(current.val + "->");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,9,9,9,9,9,9};
        int[] arr2 = new int[]{9,9,9,9};
        ListNode node = buildNode(arr);
        printListNode(node);
        ListNode node2 = buildNode(arr2);
        printListNode(node2);
        ListNode newNode = addListNode(node, node2);
        printListNode(newNode);
    }


    private static ListNode buildNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new ListNode();
        }
        ListNode first = new ListNode(0);
        ListNode current = first;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                first.val = arr[i];
            } else {
                current.next = new ListNode(arr[i]);
                current = current.next;
            }
        }
        return first;
    }



}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int data) {
        this.val = data;
    }

    public ListNode(int data, ListNode next) {
        this.val = data;
        this.next = next;
    }
}
