package yc.first;

import my_code_sum_up.code_linkedlist.util.ListNode;

//19. 删除链表的倒数第N个节点
public class Test19 {

    //1. 哑节点遍历
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); //设置一个哑节点
        dummy.next = head;
        //搜索出链表的长度
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length = length - n;
        first = dummy;
        while (length > 0) {
            first = first.next;
            length--;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    //2. 双指针一次遍历
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0); //设置一个哑节点
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }



    //二刷 —— 常规解法
    public ListNode removeNthFromEnd3(ListNode head, int n){
        //设置哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //寻找当前链表的长度
        int len = 0;
        ListNode temp = head;
        while(temp != null){
            len++;
            temp = temp.next;
        }
        //要返回倒数第k个节点，起始就是查找正数第len - k + 1个节点
        //查找要删除节点的上一个节点
        temp = dummy;
        int index = len - n + 1;
        while(index != 1){
            temp = temp.next;
            index--;
        }
        //删除当前的节点
        temp.next = temp.next.next;
        return dummy.next;
    }
}


