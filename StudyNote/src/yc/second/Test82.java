package yc.second;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.LinkedHashMap;

public class Test82 {
    /**
     *
     * @param head
     * @return
     */
/*    public ListNode deleteDuplicates(ListNode head) {
        //设置哑节点，并创建start和end两个辅助节点指针
        // start指向头节点， end从头节点开始
        //end，遍历链表，如果end.next.val == end.val;，那么end向后移动，直到end.next.val != end.val结束
        //将start指向end.next， end指向end.next 完成一轮遍历，


        //1. 边界调价判断
        if (head == null || head.next == null) {
            return head;
        }
        //2. 创建哑节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //3. 创建辅助指针
        ListNode start = dummy;
        ListNode end = head;
        while (end != null) {
            if (end.next != null && end.next.val == end.val) {
                //如果当前节点位的下一个节点不为空，且下一个节点与当前节点的值相同，则end节点后移直到找到前后值不一样为止
                while (end.next != null && end.next.val == end.val) {
                    end = end.next;
                }
                start.next = end.next;
                end = end.next;
            } else {
                // 如果不是前后节点均向后移动
                start = start.next;
                end = end.next;
            }
        }
        return dummy.next;
    }*/


    //二刷——————使用linkedHashMap来存储中间值————时间/空间都略高
    public ListNode deleteDuplicates(ListNode head) {
        //设置哑节点
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        //遍历head链表
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        while (head != null) {
            int value = map.getOrDefault(head.val, 0);
            map.put(head.val, value + 1);
            head = head.next;
        }
        //遍历map，创建新的链表
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == 1) {
                temp.next = new ListNode(integer);
                temp = temp.next;
            }
        }
        //返回结果
        return dummy.next;
    }

    //二刷—— 双指针辅助遍历
    public ListNode deleteDuplicates2(ListNode head) {
        //首先进行边界条件判断
        if(head == null || head.next == null){
            return head;
        }
        //创建哑节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //创建辅助双指针节点
        ListNode start = dummy;
        ListNode end = head;
        while(end != null){
            //如果当前的节点的下一个节点为空或者值相同，则将指针向后移，然后更改指针
            if(end.next != null && end.next.val == end.val){
                while(end.next != null && end.next.val == end.val){
                    end = end.next;
                }
                start.next = end.next;
                end = end.next;
            }else{
                start = start.next;
                end = end.next;
            }
        }
        return dummy.next;
    }
}
