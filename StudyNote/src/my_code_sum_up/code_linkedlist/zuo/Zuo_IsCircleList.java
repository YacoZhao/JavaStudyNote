package my_code_sum_up.code_linkedlist.zuo;

import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 * 左神基础课————判断链表是否有环，有的话返回入环节点，没有则返回null
 */
public class Zuo_IsCircleList {

    public static ListNode hasCircle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return null;

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast != slow){
            if(fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
