package yc.second;

import my_code_sum_up.code_linkedlist.util.ListNode;

public class Test61 {
    /**
     * 方法一：（自己写，时间复杂度较高）
     * 基本思想： 找出链表的尾部，循环k次，与头进行交换
     * @param head
     * @param k
     * @return
     */
    /*public ListNode rotateRight(ListNode head, int k) {
        //1. 找出此l链表的长度
        int len = 0;
        ListNode temp = head;
        while(temp != null){
            len++;
            temp = temp.next;
        }
        //2. 边界条件判断
        if(len <= 1 || k == 0){
            return head;
        }
        //3.调整循环次数
        int c = k % len;
        //3. 循环查找k次
        ListNode start = head;
        for(int i = 0; i < c; i++){
            //3.1定义一个头节点
            ListNode pre = start;
            //3.2向右查找到倒数第二个节点
            for(int j = 1 ; j < len - 1;j++){
                pre = pre.next;
            }
            //3.3交换
            pre.next.next = start;
            start = pre.next;
            pre.next = null;
        }
        //4. 返回结果
        return start;
    }*/

    /**
     * 2. 官方解法
     * @param head
     * @param k
     * @return
     */
    /*public ListNode rotateRight2(ListNode head, int k) {
        //1. 边界条件判断
        if (head == null) return null;
        if (head.next == null) return head;
        //2. 找出链表的长度，以及将链表尾与头连接构成环
        ListNode old_fails = head;
        int n = 1;  //表示链表长度
        while (old_fails.next != null) {
            old_fails = old_fails.next;
            n++;
        }
        old_fails.next = head;
        //3. 找出要旋转的节点
        ListNode new_fails = head;
        for (int i = 0; i < n - k % n - 1; i++) {
            new_fails = new_fails.next;
        }
        //4. 拆开交换操作
        ListNode new_start = new_fails.next;
        new_fails.next = null;
        //5. 返回结果
        return new_start;
    }*/

    //二刷——
    public ListNode rotateRight(ListNode head, int k) {
        //1. 边界条件判断
        if (head == null) return null;
        if (head.next == null) return head;
        //2. 找出链表的长度，以及将链表尾与头连接构成环
        ListNode old_fails = head;
        int n = 1;  //表示链表长度
        while (old_fails.next != null) {
            old_fails = old_fails.next;
            n++;
        }
        old_fails.next = head;
        //3. 找出要旋转的节点
        ListNode new_fails = head;
        for (int i = 0; i < n - k % n - 1; i++) {
            new_fails = new_fails.next;
        }
        //4. 拆开交换操作
        ListNode new_start = new_fails.next;
        new_fails.next = null;
        //5. 返回结果
        return new_start;
    }
}

