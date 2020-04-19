package yc.first;

import my_code_sum_up.code_linkedlist.util.ListNode;

//25. K 个一组翻转链表
public class Test25 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode temp = head;
        temp.next = new ListNode(2);
        ListNode res = reverseKGroup(head, 2);
        System.out.println(res);
    }

    private static ListNode reverseKGroup(ListNode head, int k) {
        //首先计算出此链表的长度
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        //判断是否需要反转
        if (k <= len) {
            int depth = len / k;   //判断反转的次数，也就是深度
            return reverseSearch(head, k, 0, depth);
        }
        return head;
    }

    //递归进行反转，一次反转k个
    private static ListNode reverseSearch(ListNode head, int k, int cur, int depth) {
        if (cur == depth) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        ListNode temp = head;
        int count = k;
        while (count != 0) {
            next = temp.next;     //保存当前节点的下一个节点
            temp.next = pre;
            pre = temp;
            temp = next;
            count--;
        }
        head.next =  reverseSearch(temp,k,cur+1,depth);
        return pre;
    }
}
