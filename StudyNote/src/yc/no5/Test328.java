package yc.no5;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test328 {
    public ListNode oddEvenList(ListNode head) {
        //边界条件判断
        if(head == null || head.next == null){
            return head;
        }
        //改造为奇偶两个链表
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
