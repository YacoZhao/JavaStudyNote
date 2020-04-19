package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

//160. 相交链表
public class LeeCode160 {

    // 左神————解法
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int n = 0;
        ListNode tA = headA;
        ListNode tB = headB;
        while (tA.next != null) {
            n++;
            tA = tA.next;
        }
        while (tB.next != null) {
            n--;
            tB = tB.next;
        }
        if (tA != tB) return null;
        tA = n > 0 ? headA : headB;
        tB = tA == headA ? headB : headA;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            tA = tA.next;
        }
        while (tA != tB) {
            tA = tA.next;
            tB = tB.next;
        }
        return tA;
    }

    // 方法二————评论区（双指针解法）
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tA = headA;
        ListNode tB = headB;
        while(tA != tB){
            tA = tA == null ? headB : tA.next;
            tB = tB == null ? headA : tB.next;
        }
        return tA;
    }
}
