package yc.second;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test92 {
    /*public ListNode reverseBetween(ListNode head, int m, int n) {
        //创建一个哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //找到开始交换节点的上一个节点
        ListNode node = dummy;
        for (int i = 1; i < m; i++) {
            node = node.next;
        }
        //从m的位置开始交换到n的位置
        ListNode nextHead = node.next;
        ListNode pre = null;
        ListNode next = null;
        for (int i = m; i <= n; i++) {
            next = nextHead.next;
            nextHead.next = pre;
            pre = nextHead;
            nextHead = next;
        }
        // 遍历交换结束，更改node节点指向的元素
        node.next.next = next;
        node.next = pre;
        return dummy.next;
    }*/


    //二刷——————反转单链表
    public ListNode reverseBetween(ListNode head, int m, int n){
        // 首先边界条件判断(m和n相等时，不用反转)
        if(n == m){
            return head;
        }
        //设置一个哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //找到起始的m位置节点的上一个节点
        ListNode start = dummy;
        for (int i = 1; i < m; i++) {
            start = start.next;
        }
        ListNode end = start.next;   //定义尾节点
        //从start.next开始反转链表
        ListNode next;
        ListNode pre = null;
        ListNode cur = start.next;
        int count = n - m + 1;
        while(count != 0){
            next= cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count--;
        }
        start.next = pre;
        end.next = cur;
        return dummy.next;
    }
}
