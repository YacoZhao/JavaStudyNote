package yc.no6;

import java.util.Stack;

public class Test430 {
    public Node flatten(Node head) {
        Node temp = head;
        Stack<Node> stack = new Stack<>();
        while (temp.next != null || temp.child != null) {
            if (temp.child != null) {
                if (temp.next != null) {
                    stack.push(temp.next);
                    temp.next = null;
                }
                temp.next = temp.child;
                temp.child = null;
            }
            temp = temp.next;
        }
        while (stack.size() != 0) {
            temp.next = flatten(stack.pop());
            temp.next.prev = temp;
            while (temp.next != null) {
                temp = temp.next;
            }
        }
        return head;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
