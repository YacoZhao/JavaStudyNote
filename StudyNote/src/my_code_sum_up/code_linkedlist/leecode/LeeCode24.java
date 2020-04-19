package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

// 24. 两两交换链表中的节点
public class LeeCode24 {

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
