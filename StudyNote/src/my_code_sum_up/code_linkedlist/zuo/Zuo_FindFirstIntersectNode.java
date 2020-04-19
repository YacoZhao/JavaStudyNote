package my_code_sum_up.code_linkedlist.zuo;

import my_code_sum_up.code_linkedlist.util.ListNode;

/**
 * 查找两个链表的第一个相交节点
 */
public class Zuo_FindFirstIntersectNode {

    public static ListNode getIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) return null;


        ListNode loop1 = Zuo_IsCircleList.hasCircle(head1);
        ListNode loop2 = Zuo_IsCircleList.hasCircle(head2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(head1,head2,loop1,loop2);
        }
        return null;  // 一个有环一个没有环肯定不相交
    }

    private static ListNode bothLoop(ListNode head1, ListNode head2, ListNode loop1, ListNode loop2) {
        ListNode p1 = head1;
        ListNode p2 = head2;
        if(loop1 == loop2){
            int n = 0;
            while (p1 != loop1) {
                n++;
                p1 = p1.next;
            }
            while (p2 != loop2) {
                n--;
                p2 = p2.next;
            }
            p1 = n > 0 ? head1 : head2;
            p2 = p1 == head1 ? head2 : head1;
            for (int i = 0; i < Math.abs(n); i++) {
                p1 = p1.next;
            }
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }else{
            p1 = loop1;
            while(p1.next != loop1){
                if(p1.next == loop2) return loop1;
                p1 = p1.next;
            }
            return null;
        }
    }

    // 查找两个无环单链表的相交节点
    private static ListNode noLoop(ListNode head1, ListNode head2) {
        ListNode p1 = head1;
        ListNode p2 = head2;

        int n = 0;
        while (p1.next != null) {
            n++;
            p1 = p1.next;
        }
        while (p2.next != null) {
            n--;
            p2 = p2.next;
        }
        if(p1 != p2) return null;
        p1 = n > 0 ? head1 : head2;
        p2 = p1 == head1 ? head2 : head1;
        for (int i = 0; i < Math.abs(n); i++) {
            p1 = p1.next;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);
    }

}
