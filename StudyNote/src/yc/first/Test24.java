package yc.first;

import my_code_sum_up.code_linkedlist.util.ListNode;

//24. 两两交换链表中的节点
public class Test24 {

    //方法一： 递归
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    //方法二： 迭代
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        pre.next = head;
        while (pre.next != null && pre.next.next != null) {
            ListNode start = pre.next;
            ListNode end = pre.next.next;
            start.next = end.next;
            end.next = start;
            pre.next = end;
            pre = start;
        }
        return dummy.next;
    }

    //二刷————————递归的思想
    public ListNode swapPairs3(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        //交换前两个元素的值，然后递归到下一层
        int temp = head.val;
        head.val = head.next.val;
        head.next.val = temp;
        head.next.next = swapPairs3(head.next.next);
        return head;
    }

    //二刷————————递归的思想
    public ListNode swapPairs4(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        //交换前两个元素的值，然后递归到下一层
        ListNode new_head = head.next;
        ListNode next = head.next.next;
        new_head.next = head;
        head.next = swapPairs4(next);
        return new_head;
    }
}
