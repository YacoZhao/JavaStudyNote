package my_code_sum_up.code_linkedlist.zuo;

import my_code_sum_up.code_linkedlist.util.DoubleNode;
import my_code_sum_up.code_linkedlist.util.LinkedUtils;
import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 * 反转单双向链表
 *
 */
public class Zuo_ReverseList {

    // 非递归实现反转单链表
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode next = head.next;
        ListNode last = reverseList(next);
        head.next = null;
        next.next = head;

        return last;
    }

    // 非递归实现反转单链表
    public static ListNode reverseList2(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode pre = null;
        ListNode next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 反转双向链表
    public static DoubleNode reverseList(DoubleNode head){
        if(head == null || head.next == null) return head;

        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    //测试反转效果
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        LinkedUtils.printLinkedList(head1);
        head1 = reverseList2(head1);
        LinkedUtils.printLinkedList(head1);


        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        LinkedUtils.printDoubleLinkedList(head2);
        LinkedUtils.printDoubleLinkedList(reverseList(head2));
    }
}
