package yc.third;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test148 {
    /**
     * test148 —— 归并排序 —— 递归解法
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        //结束条件
        if (head == null || head.next == null) {
            return head;
        }
        //快慢指针找到中间节点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        //将链表改造为左右两个子链表，并格子对他们进行排序
        ListNode left = sortList(head);
        right = sortList(right);
        //排序结束之后，将两个子链表进行合并
        //设置哑节点用于最后的返回
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        temp.next = left == null ? right : left;
        return dummy.next;
    }


    //归并排序链表（手撸）
    public ListNode sortList1(ListNode head) {
        if(head == null || head.next == null) return head;

        //快慢指针将链表拆分成为左右两个链表
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next; // 代表右边的链表
        slow.next = null; // 代表左边的链表
        ListNode left = sortList1(head);
        ListNode right = sortList1(fast);

        //合并两个链表
        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if(left == null) return right;
        if(right == null) return left;
        ListNode cl = left;
        ListNode cr = right;
        if(cl.val < cr.val){
            cl.next = merge(left.next,right);
            return cl;
        }else{
            cr.next = merge(left,right.next);
            return cr;
        }
    }
}
