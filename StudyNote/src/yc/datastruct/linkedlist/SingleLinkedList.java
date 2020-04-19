package yc.datastruct.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;

//单链表类
public class SingleLinkedList {
    private Node head;

    /**
     * 链表尾添加节点
     * @param data
     */
    public void addData(int data){
        if(head == null){
            head = new Node(data);
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(data);
    }

    /**
     * 获取单链表的长度
     * @return
     */
    public int length(){
        int res = 0;
        Node temp = head;
        while(temp != null){
            temp = temp.next;
            res++;
        }
        return res;
    }

    /**
     * 删除指定位置的节点，头节点算第1个接节点
     * @param index
     */
    public void deleteNode(int index){
        //首先判断index是否复合逻辑
        if(index < 1 || index > length()){
            System.out.println("删除的位置不合理!");
            return;
        }
        if(index == 1){   //如果删除第一个节点，直接将头指针向后移
            head = head.next;
            return;
        }
        Node temp = head;
        int count = index - 2;
        while(count != 0){
            temp = temp.next;
            count--;
        }
        temp.next = temp.next.next;
    }

    /**
     * 遍历列表进行输出
     */
    public void traverse(){
        if(head == null){
            System.out.println("链表为空~~");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    /**
     * 指定位置插入节点
     * @param index  这里index指元素插入后，该元素在链表中的位置，位置从1开始计数
     * @param value
     */
    public void insertNode(int index, int value){
        //首先判断index是否复合逻辑
        if(index < 1 || index > length() + 1){
            System.out.println("参数输入有误~~~无法插入");
            return;
        }
        Node cur = new Node(value);
        Node temp = head;
        if(index == 1){
            cur.next = head;
            head = cur;
            return;
        }
        int count = index - 2;
        while(count != 0){
            temp = temp.next;
            count--;
        }
        cur.next = temp.next;
        temp.next = cur;
    }

    /**
     * 对链表进行排序
     */
    public void sortLinkList(){
        //如果链表为空，不同排序
        if(length() <= 1){
            return;
        }
        //冒泡排序算法进行排序
        Node curNode;
        Node nextNode = head.next;
        for(curNode = head ; curNode.next != null; curNode = curNode.next){
            for(nextNode = curNode.next; nextNode != null; nextNode = nextNode.next){

            }
        }
    }

}
