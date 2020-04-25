package yc.al.zuo.base;

import java.util.Stack;

/**
 * 判断一个链表是不是回文链表
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 11:12
 */
public class Class3_Code10_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space(通过辅助栈来遍历)
    public static boolean isPalindrome1(Node head) {
        if(head == null || head.next == null) return true;
        Stack<Node> stack  = new Stack<>();
        Node cur = head;
        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while(head != null){
            if(head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 通过n/2辅助空间来实现
    public static boolean isPalindrome2(Node head) {
        if(head == null || head.next == null) return true;
        Stack<Node> stack = new Stack<>();
        // 快慢指针找到数组的中点
        Node slow = head.next;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow即为链表的中点
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        // 比较是否为回文链表
        while(!stack.isEmpty()) {
            if(head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 通过常数空间复杂度来实现(时间复杂度N，空间复杂度1)
    public static boolean isPalindrome3(Node head) {
        if(head == null || head.next == null) return true;
        Node n1 = head;
        Node n2 = head;
        while(n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        n2 = n1.next;   // 指向后半部分链表的头节点
        n1.next = null;  // n1指向null，断开链表
        Node n3 = null;
        while(n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1; // n3存放后半部分链表的头节点
        n2 = head; //n2 存放前部分链表的头节点
        boolean res = true;
        // 开始比较后面反转过后的链表与前面的是否相等
        while(n1 != null && n2 != null) {
            if(n1.value != n2.value) {
                res = false;
            }
            n1 = n1.next;
            n2 = n2 .next;
        }
        // 将原来反转了的链表再改造回去
        n1 = n3.next;
        n3.next = null;
        while(n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
