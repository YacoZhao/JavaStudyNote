package my_code_sum_up.code_linkedlist.zuo;

import my_code_sum_up.code_linkedlist.util.LinkedUtils;
import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 *
 * 左神基础课————打印两个有序连表达的公共部分
 *
 * 给定两个有序链表的头指针head1和head2，打印两个
 * 链表的公共部分。
 */
public class Zuo_PrintCommonPart {

    // 打印两个有序链表的公共部分
    public static void commonPart(ListNode head1, ListNode head2){
        System.out.println("Print commonPart!");
        while(head1 != null && head2 != null){
            if(head1.val < head2.val){
                head1 = head1.next;
            }else if(head1.val > head2.val){
                head2 = head2.next;
            }else{
                System.out.println(head1.val + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(5);
        node1.next.next.next = new ListNode(6);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = new ListNode(5);
        node2.next.next.next = new ListNode(7);
        node2.next.next.next.next = new ListNode(8);

        LinkedUtils.printLinkedList(node1);
        LinkedUtils.printLinkedList(node2);
        commonPart(node1, node2);
    }
}
