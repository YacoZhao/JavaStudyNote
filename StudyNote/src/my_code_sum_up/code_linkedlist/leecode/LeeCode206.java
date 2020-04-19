package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

// 反转单链表
public class LeeCode206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            ListNode new_head = head;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
