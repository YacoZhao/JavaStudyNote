package my_code_sum_up.code_linkedlist.leecode;

import java.util.Stack;

//430. 扁平化多级双向链表
public class LeeCode438 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    // 递归迭代
    public Node flatten(Node head) {
        if (head == null) return head;
        // 如果当前节点没有孩子，则直接扁平化下一个
        if (head.child == null) {
            flatten(head.next);
            return head;
        }
        // 到此处移动有孩子
        Node next = head.next;
        Node curChild = flatten(head.child); // 扁平化孩子
        head.child = null;
        head.next = curChild;
        curChild.prev = head;
        while (curChild.next != null) {
            curChild = curChild.next;
        }
        curChild.next = next;
        if (next != null) {
            next.prev = curChild;
        }
        flatten(next);
        return head;
    }
}
