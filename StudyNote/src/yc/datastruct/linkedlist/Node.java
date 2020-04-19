package yc.datastruct.linkedlist;

public class Node {
    int value;
    Node next;

    public Node(){}

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
