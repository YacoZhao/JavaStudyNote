package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//61. 旋转链表
public class LeeCode61 {
    public ListNode rotateRight(ListNode head, int k) {
        // 首先先将链表做成环
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        int n = 1;
        while (cur.next != null) {
            n++;
            cur = cur.next;
        }
        cur.next = head;

        // 然后从环中找断点
        cur = head;
        for (int i = 0; i < n - (k % n) - 1; i++) {
            cur = cur.next;
        }
        ListNode next = cur.next;
        cur.next = null;
        return next;
    }
}
