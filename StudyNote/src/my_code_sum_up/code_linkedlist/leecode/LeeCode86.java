package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.LinkedUtils;
import my_code_sum_up.code_linkedlist.util.ListNode;

//86. 分隔链表
public class LeeCode86 {
    public static ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;

        ListNode sH = null;
        ListNode sT = null;
        ListNode bH = null;
        ListNode bT = null;

        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = null;
            if(head.val < x){
                if(sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = sT.next;
                }
            }else{
                if(bH == null){
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bT = bT.next;
                }
            }
            head = next;
        }
        // 合并两个链表
        if(sT != null){
            sT.next = bH;
        }
        return sH == null ? bH : sH;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        LinkedUtils.printLinkedList(head);

        LinkedUtils.printLinkedList(partition(head,3));
    }
}
