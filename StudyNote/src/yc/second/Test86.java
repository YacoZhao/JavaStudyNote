package yc.second;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test86 {
    /*public ListNode partition(ListNode head, int x) {
        //1. 找到链表中第一个大于等于x的节点
        //2.从当前节点的下一个节点开始，如果小于x，就把节点拿出来插入到当前的位置

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode goals = dummy;
        while (goals.next != null && goals.next.val < x) {
            goals = goals.next;
        }
        if (goals.next == null) {
            return head;
        }
        ListNode temp = goals.next; //移动的节点
        ListNode pre = goals;  //从此节点插入链表
        ListNode end = temp;   //转换后插入此节点前面

        while (temp != null) {
            if (temp.next.val < x) {
                ListNode cur = new ListNode(temp.next.val);
                temp.next = temp.next.next;
                pre.next = cur;
                cur.next = end;
                pre = pre.next;
            } else {
                temp = temp.next;
            }
        }
        return dummy.next;
    }*/

    /**
     * 解法二： 将将所有小于val的节点搜集构成链表1，然后搜集所有大于等于val的节点构成链表2，然后合并两个链表
     * @param head
     * @param x
     * @return
     */
    /*public ListNode partition2(ListNode head, int x){
        ListNode dummy_left = new ListNode(0);
        ListNode dummy_right = new ListNode(0);
        ListNode left = dummy_left;
        ListNode right = dummy_right;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x) {
                left.next = new ListNode(temp.val);
                left = left.next;
            } else {
                right.next = new ListNode(temp.val);
                right = right.next;
            }
            temp = temp.next;
        }
        ListNode cur = dummy_left;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = dummy_right.next;
        return dummy_left.next;
    }*/

    //二刷——————创建辅助mid中间节点
    public ListNode partition(ListNode head, int x){
        if (head == null || head.next == null) {
            return head;
        }
        //创建哑节点
        ListNode dummy = new ListNode(0);
        //创建辅助指针
        ListNode mid = new ListNode(x);
        ListNode end = mid;
        dummy.next = mid;
        ListNode temp = dummy;
        //遍历链表
        while (head != null) {
            if (head.val < x) {
                temp.next = new ListNode(head.val);
                temp = temp.next;
                temp.next = mid;
            } else {
                end.next = new ListNode(head.val);
                end = end.next;
            }
            head = head.next;
        }
        //删除辅助的mid的节点
        ListNode cur = dummy;
        while (cur.next.val != x) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }
}
