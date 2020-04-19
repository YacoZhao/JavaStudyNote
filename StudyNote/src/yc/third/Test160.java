package yc.third;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Test160 {
    /**
     * hash表存储
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界情况
        if (headA == null || headB == null) {
            return null;
        }
        // 借助HashSet存储已经找到的节点
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        // 遍历headB查看是否可以插入
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        //如果没有找到交点，则返回null
        return null;
    }

    /**
     * 方法二： 双指针遍历法
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }
}
