package yc.no4;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test206 {

    /**
     * 反转单链表——————递归求解
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 反转单链表——————辅助节点迭代求解
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
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
