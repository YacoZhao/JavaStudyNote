package my_code_sum_up.code_linkedlist.zuo;

import my_code_sum_up.code_linkedlist.util.LinkedUtils;
import my_code_sum_up.code_linkedlist.util.ListNode;
import sun.awt.windows.ThemeReader;

import java.util.Stack;

/**
 * 左神基础课————判断一个链表是否是回文链表
 */
public class Zuo_IsPalindromeList {

    // 方法一： 利用辅助栈的思想
    public static boolean isPalindromeList1(ListNode head) {
        if (head == null || head.next == null) return true;
        //遍历一边链表，节点全部压入栈中
        Stack<Integer> stack = new Stack<Integer>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.val != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // 方法二： 不接任何辅助空间
    public static boolean isPalindromeList2(ListNode head){
        if (head == null || head.next == null) return true;
        //快慢指针走到链表中间
        ListNode n1 = head;
        ListNode n2 = head;
        while(n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next = null;
        ListNode n3 = null;
        // 反转后面的链表
        while(n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        // 到这里head表示前面的链表，n1表示后面的连败哦
        n3 = n1;
        n2 = head;
        boolean res = true;
        while(n2 != null && n1 != null){
            if(n2.val != n1.val){
                res = false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 比较结束之后将链表反转回来
        n1 = null;
        while(n3 != null){
            n2 = n3.next;
            n3.next = n1;
            n1 = n3;
            n3 = n2;
        }
        return res;
    }

    public static void main(String[] args) {

        ListNode head = null;
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(2);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(1);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        LinkedUtils.printLinkedList(head);
        System.out.print(isPalindromeList1(head) + " | ");
        System.out.println(isPalindromeList2(head) + " | ");
        LinkedUtils.printLinkedList(head);
        System.out.println("=========================");

    }

}
