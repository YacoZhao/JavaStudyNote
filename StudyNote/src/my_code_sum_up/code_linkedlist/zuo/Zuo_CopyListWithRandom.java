package my_code_sum_up.code_linkedlist.zuo;

import java.util.HashMap;

/**
 * 左神基础课——复制含有随机指针节点的链表
 *
 *
 */
public class Zuo_CopyListWithRandom {
    public static class RandomNode {
        public int value;
        public RandomNode next;
        public RandomNode rand;

        public RandomNode(int data) {
            this.value = data;
        }
    }

    public static RandomNode copyListWithRand1(RandomNode head) {
        // 构建哈希表
        HashMap<RandomNode,RandomNode> map = new HashMap<RandomNode,RandomNode>();

        RandomNode cur = head;

        while(cur != null){
            map.put(cur,new RandomNode(cur.value));
            cur = cur.next;
        }

        cur = head;
        while(cur != null){
            map.get(cur).next = cur.next != null ? map.get(cur.next) : null;
            map.get(cur).rand = cur.rand != null ? map.get(cur.rand) : null;
            cur = cur.next;
        }

        return map.get(head);
    }

    public static void printRandLinkedList(RandomNode head) {
        RandomNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RandomNode head = null;
        RandomNode res1 = null;
        RandomNode res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand1(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new RandomNode(1);
        head.next = new RandomNode(2);
        head.next.next = new RandomNode(3);
        head.next.next.next = new RandomNode(4);
        head.next.next.next.next = new RandomNode(5);
        head.next.next.next.next.next = new RandomNode(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand1(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }
}
