package yc.datastruct.linkedlist;

//单链表的测试函数
public class SLLTest {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        System.out.println("添加元素之前：");
        linkedList.traverse();
        System.out.println("添加元素之后：");
        linkedList.addData(2);
        linkedList.addData(8);
        linkedList.addData(1);
        linkedList.addData(4);
        linkedList.addData(3);
        linkedList.addData(6);
        linkedList.addData(7);
        linkedList.traverse();
        System.out.println("链表长度为：" + linkedList.length());
        //测试删除第3个位置的节点
        linkedList.deleteNode(8);
        System.out.println("删除元素之后：");
        linkedList.traverse();
        //测试按照指定位置插入节点是否正确
        linkedList.insertNode(8,9);
        System.out.println("插入元素之后：");
        linkedList.traverse();
    }
}
