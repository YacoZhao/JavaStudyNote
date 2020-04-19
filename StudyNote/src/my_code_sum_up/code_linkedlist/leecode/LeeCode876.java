package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//876. 链表的中间结点
public class LeeCode876 {

    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
