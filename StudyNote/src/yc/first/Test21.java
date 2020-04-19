package yc.first;

import my_code_sum_up.code_linkedlist.util.ListNode;

//21. 合并两个有序链表
public class Test21 {

    //方法一：递归
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    //方法二：迭代
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode pre = dummy;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    pre.next = l1;
                    l1 = l1.next;
                } else {
                    pre.next = l2;
                    l2 = l2.next;
                }
                pre = pre.next;
            }
            if (l2 != null) {
                pre.next = l2;
            }
            if (l1 != null) {
                pre.next = l1;
            }
            return dummy.next;
    }
    //二刷 —— 常规解法
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2){
        // 边界条件判断
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 设置哑节点
        ListNode dummy = new ListNode(0);
        // 循环遍历两个链表
        ListNode temp = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                temp.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            temp = temp.next;
        }
        if (l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }
        return dummy.next;
    }

    //二刷—— 递归的解法
    public ListNode mergeTwoLists4(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }

        if(l1.val < l2.val){
            l1.next = mergeTwoLists4(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists4(l1,l2.next);
            return l2;
        }
    }
}
