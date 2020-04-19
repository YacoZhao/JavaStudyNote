package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//25. K 个一组翻转链表
public class LeeCode25 {

    // k个一组反转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) return head;
        if (head == null) return head;
        ListNode cur = head;
        int n = 0;
        while (n != k - 1) {
            if (cur == null) return head;
            n++;
            cur = cur.next;
        }
        ListNode next = cur.next;
        cur.next = null;

        // 反转当前链表
        ListNode newHead = reverseList(head);
        head.next = reverseKGroup(next, k);
        return newHead;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

