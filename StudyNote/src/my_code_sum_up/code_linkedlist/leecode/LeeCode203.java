package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//203. 移除链表元素
public class LeeCode203 {


    public ListNode removeElements(ListNode head, int val) {
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode c = d;
        while (c.next != null) {
            if (c.next.val == val) {
                c.next = c.next.next;
            } else {
                c = c.next;
            }
        }
        return d.next;
    }
}
