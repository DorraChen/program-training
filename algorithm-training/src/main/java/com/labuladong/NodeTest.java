package com.labuladong;


/**
 * @author clz
 * @date 2021/2/3 16:43
 * @description
 */
public class NodeTest {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            // 迭代访问 p.val
            System.out.println(p.val);
        }
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        first.next = first;
        ListNode p = first;
        for (int i = 2; i <= 6; i++) {
            ListNode temp = new ListNode(i);
            temp.next = p;
            p.next = temp;
            p = p.next;
        }
        p.next = null;
        traverse(first);
    }
}
